package com.example.chatservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.handler.AbstractHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@EnableWebFlux
public class RouterFunctionMapping extends AbstractHandlerMapping implements InitializingBean {
    @Nullable
    private RouterFunction<?> routerFunction;

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    public RouterFunctionMapping(RouterFunction<?> routerFunction) {
        this.routerFunction = routerFunction;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CollectionUtils.isEmpty(this.messageReaders)) {
            ServerCodecConfigurer codecConfigurer = ServerCodecConfigurer.create();
            this.messageReaders = codecConfigurer.getReaders();
        }

        if (this.routerFunction == null) {
            initRouterFunctions();
        }
    }

    protected void initRouterFunctions() {
        if (logger.isDebugEnabled()) {
            logger.debug("Looking for router functions in application context: " + getApplicationContext());
        }

        List<RouterFunction<?>> routerFunctions = routerFunctions();
        if (!CollectionUtils.isEmpty(routerFunctions) && logger.isInfoEnabled()) {
            routerFunctions.forEach(routerFunction -> logger.info("Mapped " + routerFunction));
        }
        this.routerFunction = routerFunctions.stream()
                .reduce(RouterFunction::andOther)
                .orElse(null);
    }

    private List<RouterFunction<?>> routerFunctions() {
        SortedRouterFunctionsContainer container = new SortedRouterFunctionsContainer();
        obtainApplicationContext().getAutowireCapableBeanFactory().autowireBean(container);

        return CollectionUtils.isEmpty(container.routerFunctions) ? Collections.emptyList() : container.routerFunctions;
    }

    private static class SortedRouterFunctionsContainer {
        @Nullable
        private List<RouterFunction<?>> routerFunctions;

        @Autowired(required = false)
        public void setRouterFunctions(List<RouterFunction<?>> routerFunctions) {
            this.routerFunctions = routerFunctions;
        }
    }

    @Override
    protected Mono<?> getHandlerInternal(ServerWebExchange serverWebExchange) {
        return null;
    }
}
