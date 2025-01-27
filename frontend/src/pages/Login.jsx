import TextField from "@mui/material/TextField";
import { AuthBox, AuthPage, BlueButton, StyledLink } from "../styles";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const login = () => {
    axios
      .post("http://localhost:8080/api/auth/authenticate", {
        email: username,
        password,
      })
      .then((response) => {
        localStorage.setItem("token", response.data.token);
        navigate("/dashboard");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    login();
  };

  return (
    <AuthPage>
      <AuthBox>
        <h1>Log In</h1>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Username"
            variant="outlined"
            required
            fullWidth
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setPassword(e.target.value)}
          />
          <BlueButton
            type="submit"
            fullWidth
            variant="contained"
            sx={{ margin: "20px 0px" }}
          >
            Log In
          </BlueButton>
          <div>
            <span>
              Don't have an account?{" "}
              <StyledLink
                style={{ textDecoration: "none", color: "" }}
                to="/auth/register"
              >
                Register Here
              </StyledLink>
            </span>
          </div>
        </form>
      </AuthBox>
    </AuthPage>
  );
};

export default Login;
