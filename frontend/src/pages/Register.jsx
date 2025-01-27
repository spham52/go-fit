import TextField from "@mui/material/TextField";
import { AuthBox, AuthPage, BlueButton, StyledLink } from "../styles";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [username, setUsername] = useState("");
  const [displayName, setDisplayName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordMatch, setPasswordMatch] = useState(true);
  const [usernameError, setUsernameError] = useState("");
  const [displayNameError, setDisplayNameError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigate = useNavigate();

  const register = () => {
    axios
      .post("http://localhost:8080/api/auth/register", {
        username,
        displayName,
        password,
      })
      .then((response) => {
        localStorage.setItem("token", response.data.token);
        navigate("/dashboard");
      })
      .catch((err) => {
        setUsernameError(err.response.data.fieldErrors["save.user.username"]);
        setDisplayNameError(
          err.response.data.fieldErrors["save.user.displayName"]
        );
        setPasswordError(
          err.response.data.fieldErrors["save.user.plainPassword"]
        );
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (password === confirmPassword) {
      setPasswordMatch(true);
      register();
    } else {
      setPasswordMatch(false);
    }
  };

  return (
    <AuthPage>
      <AuthBox>
        <h1>Register</h1>
        <form onSubmit={handleSubmit} noValidate>
          <TextField
            label="Username"
            variant="outlined"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setUsername(e.target.value)}
            error={!!usernameError}
            helperText={usernameError || ""}
          />
          <TextField
            label="Display Name"
            variant="outlined"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setDisplayName(e.target.value)}
            error={!!displayNameError}
            helperText={displayNameError || ""}
          />
          <TextField
            id="password"
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setPassword(e.target.value)}
            error={!!passwordError}
            helperText={passwordError || ""}
          />
          <TextField
            id="confirm-password"
            error={!passwordMatch || !!passwordError}
            label="Confirm Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setConfirmPassword(e.target.value)}
            helperText={passwordMatch ? "" : "Passwords do not match"}
          />
          <BlueButton
            type="submit"
            fullWidth
            variant="contained"
            sx={{ margin: "20px 0px" }}
          >
            Sign Up
          </BlueButton>
          <div>
            <span>
              Have an account?{" "}
              <StyledLink to="/auth/login">Log In Here</StyledLink>
            </span>
          </div>
        </form>
      </AuthBox>
    </AuthPage>
  );
};

export default Register;
