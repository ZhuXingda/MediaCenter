package mc.comment.service.rpc.provider.imp;

import mc.comment.service.rpc.provider.TestProvider;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class TestProviderImpl implements TestProvider {
    @Override
    public String test() {
        return "hello world";
    }
}
