package ru.gb.trishkin.shop.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;

@Configuration("sellIntegrationConfig")
@ImportResource("classpath:/integration/http-sell-integration.xml")
public class SellIntegrationConfig {
    private DirectChannel sellsChannel;

    public SellIntegrationConfig(@Qualifier(value = "sellsChannel") DirectChannel sellsChannel) {
        this.sellsChannel = sellsChannel;
    }

    public DirectChannel getSellsChannel() {
        return sellsChannel;
    }
}
