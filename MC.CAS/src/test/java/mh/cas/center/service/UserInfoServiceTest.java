package mh.cas.center.service;

import mh.cas.center.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserInfoServiceTest {
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void createUserInfoTest() throws Exception {
        userInfoService.createUserInfo("280511682@qq.com","19960211");
    }
}
