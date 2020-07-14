import com.google.errorprone.annotations.Var;
import com.hedera.hashgraph.sdk.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Hbar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.bp.Duration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientIntegrationTest {
    @Test
    @DisplayName("setNetwork() functions correctly")
    void testReplaceNodes() {
        assertDoesNotThrow(() -> {
            @Var Map<AccountId, String> network = new HashMap<>();
            network.put(new AccountId(3), "0.testnet.hedera.com:50211");
            network.put(new AccountId(4), "1.testnet.hedera.com:50211");

            var client = IntegrationTestClientManager.getClient()
                .setMaxQueryPayment(new Hbar(2))
                .setRequestTimeout(Duration.ofMinutes(2));

            var operatorId = client.getOperatorId();
            assertNotNull(operatorId);

            // Execute two simple queries so we create a channel for each network node.
            new AccountBalanceQuery()
                .setAccountId(operatorId)
                .execute(client);

            new AccountBalanceQuery()
                .setAccountId(operatorId)
                .execute(client);

            network = new HashMap<>();
            network.put(new AccountId(4), "1.testnet.hedera.com:50211");
            network.put(new AccountId(5), "2.testnet.hedera.com:50211");

            client.setNetwork(network);

            network = new HashMap<>();
            network.put(new AccountId(4), "35.186.191.247:50211");
            network.put(new AccountId(5), "35.192.2.25:50211");

            client.setNetwork(network);

            client.close();
        });
    }
}
