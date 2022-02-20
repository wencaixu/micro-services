package com.ares.seckill.utils;

import com.ares.seckill.mapper.TUserMapper;
import com.ares.seckill.pojo.User;
import com.ares.seckill.vo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserGeneratorSimulatedLogin {

    @Autowired
    private TUserMapper userMapper;

    /**
     * 批量生成用户，并进行模拟登录
     *
     * @param userCount
     * @param path
     * @throws IOException
     */
    public void batchUserGenerator(Long userCount, String path) throws IOException {
        for (int i = 0; i < userCount; i++) {
            // 生成用户
            User user = new User();
            user.setId(13000000000L + i + 1);
            user.setSalt("xwcxwj1111");
            user.setNickname("user_" + i);
            user.setPassword(MD5Utils.inputPassToDbPass("123434", user.getSalt()));
            user.setHead("123");
            user.setLoginCount(1);

            User userQuery = userMapper.selectById(user.getId());
            if (Objects.isNull(userQuery)) {
                userMapper.insert(user);
            }
            // 模拟登陆
            trySimulatedLogin(path, user);
        }
    }

    /**
     * 模拟登陆
     *
     * @param path 文件路径
     * @throws IOException
     */
    public void simulatedLogin(String path) throws IOException {
        List<User> usersList = userMapper.selectList(null);
        for(User user : usersList){
            // 模拟登陆
            trySimulatedLogin(path, user);
        }
    }

    /**
     * 尝试模拟登陆
     *
     * @param path
     * @param user
     * @throws IOException
     */
    private void trySimulatedLogin(String path, User user) throws IOException {
        String loginUrl = "http://localhost:8080/login/doLogin";
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = new FormBody.Builder()
                .add("mobile", String.valueOf(user.getId()))
                .add("password", MD5Utils.formPassToDbPass("123434", user.getSalt()))
                .build();
        Request request = new Request.Builder().url(loginUrl).post(requestBody).build();
        Response response = client.newCall(request)
                .execute();
        String responseBody = Objects.requireNonNull(response.body()).string();
        RespBean respBean = objectMapper.readValue(responseBody, RespBean.class);
        String content = user.getId() + "," + respBean.getObj();
        appendCookieEof(path, content, true);
    }

    private void appendCookieEof(String path, String content, boolean isAppend) {
        try {
            File cookieFiles = new File(path);
            if (!cookieFiles.exists()) {
                boolean ignore = cookieFiles.createNewFile();
            }
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(cookieFiles, isAppend))) {
                pw.println(content);
                pw.flush();
            }
        } catch (IOException e) {
            log.info("io exception {}", e.getMessage());
        }
    }
}
