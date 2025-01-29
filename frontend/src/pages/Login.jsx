import TextField from "@mui/material/TextField";
import { AuthBox, AuthPage, BlueButton, StyledLink } from "../styles";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const login = () => {
    axios
      .post("http://localhost:8080/api/auth/authenticate", {
        username,
        password,
      })
      .then((response) => {
        localStorage.setItem("token", response.data.token);
        navigate("/dashboard");
      })
      .catch((err) => {
        if (err.response.data.fieldErrors) {
          setUsernameError(err.response.data.fieldErrors["username"]);
          setPasswordError(err.response.data.fieldErrors["password"]);
        } else {
          setError(err.response.data.error);
        }
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
        <form onSubmit={handleSubmit} noValidate>
          <TextField
            label="Username"
            variant="outlined"
            required
            fullWidth
            onChange={(e) => setUsername(e.target.value)}
            error={!!usernameError || !!error}
            helperText={usernameError || ""}
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setPassword(e.target.value)}
            error={!!passwordError || !!error}
            helperText={passwordError || error || ""}
            autoComplete="off"
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
