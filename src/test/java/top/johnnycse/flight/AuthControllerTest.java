package top.johnnycse.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import top.johnnycse.flight.dto.request.RegisterRequest;
import org.junit.runner.RunWith;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister_Success() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("newuser");
        request.setPassword("password123");
        request.setPhone("13912345678");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())  // HTTP 200 OK
                .andExpect(jsonPath("$.status").value("ok"))  // Check response status
                .andExpect(jsonPath("$.data").value("注册成功"));
    }

    @Test
    public void testRegister_UsernameExists() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("existinguser");
        request.setPassword("password123");
        request.setPhone("13912345678");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())  // HTTP 400 Bad Request
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("用户名已存在"));
    }

    @Test
    public void testRegister_InvalidPhone() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("newuser");
        request.setPassword("password123");
        request.setPhone("123");  // Invalid phone number

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())  // HTTP 400 Bad Request
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("手机号格式不正确"));
    }
}
