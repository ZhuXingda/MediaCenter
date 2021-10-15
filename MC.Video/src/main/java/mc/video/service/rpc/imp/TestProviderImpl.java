package mc.video.service.rpc.imp;

import mc.video.service.rpc.TestProvider;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class TestProviderImpl implements TestProvider {
    @Override
    public String test() {
        return "hello world";
    }
}
