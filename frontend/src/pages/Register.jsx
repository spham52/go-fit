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
        console.log(err);
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
        <form onSubmit={handleSubmit}>
          <TextField
            label="Username"
            variant="outlined"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            label="Display Name"
            variant="outlined"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setDisplayName(e.target.value)}
          />
          <TextField
            id="password"
            error={!passwordMatch}
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
            onChange={(e) => setPassword(e.target.value)}
          />
          <TextField
            id="confirm-password"
            error={!passwordMatch}
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
