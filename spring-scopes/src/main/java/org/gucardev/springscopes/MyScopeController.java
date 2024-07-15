package org.gucardev.springscopes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scopes")
@Slf4j
public class MyScopeController {

    private final SingletonScope singletonScope1;
    private final SingletonScope singletonScope2;

    private final PrototypeScope prototypeScope1;
    private final PrototypeScope prototypeScope2;

    @GetMapping
    public void getBroker() {
        // SINGLETON SCOPE - her istendiginde ayni insatance i dondurur, defualt olarak singleton
        singletonScope1.setValue("val1");
        // singletonScope1 will be overridden by singletonScope2
        // because spring gives one instance for both
        singletonScope2.setValue("val2");
        log.info("SINGLETON | singletonScope1: {} - singletonScope2: {} singletonScope1==singletonScope2 => {}", singletonScope1.getValue(), singletonScope2.getValue(),
                singletonScope1.getValue().equals(singletonScope2.getValue()));
        // -----------------------------------------

        // @Scope("prototype") PROTOTYPE SCOPE - multithread seylerde kullanilabilir
        prototypeScope1.setValue("val1");
        // singletonScope1 will be overridden by singletonScope2
        // because spring gives one instance for both
        prototypeScope2.setValue("val2");
        log.info("SINGLETON | prototypeScope1: {} - prototypeScope2: {} prototypeScope1==prototypeScope2 => {}", prototypeScope1.getValue(), prototypeScope2.getValue(),
                prototypeScope1.getValue().equals(singletonScope2.getValue()));
        // @Scope("request") REQUEST SCOPE - request basladigindan bitene kadar surede olusup yok edilen instance
        // @Scope("session") SESSION SCOPE - session basladigindan bitene kadar surede olusup yok edilen instance
        // @Scope("application") Gcreates the bean instance for the lifecycle of a ServletContext.
        // @Scope("websocket") websocket
    }


}
