package mc.user.service.service;

import mc.cas.center.service.UserInfoService;
import mc.user.service.TestConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserInfoServiceTest {
    @DubboReference
    UserInfoService userInfoService;

    @Test
    public void createUserInfoTest() throws Exception {
        userInfoService.createUserInfo("280511682@qq.com","19960211");
    }
}
