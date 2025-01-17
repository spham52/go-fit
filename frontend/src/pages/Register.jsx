import "../auth.css";
import TextField from "@mui/material/TextField";
import { BlueButton, StyledLink } from "../styles";

const Register = () => {
  return (
    <>
      <div className="auth-box">
        <h1>Register</h1>
        <form>
          <TextField
            label="Username"
            variant="outlined"
            margin="normal"
            required
            fullWidth
          />
          <TextField
            label="Display Name"
            variant="outlined"
            margin="normal"
            required
            fullWidth
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
          />
          <TextField
            label="Confirm Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
          />
          <BlueButton fullWidth variant="contained" sx={{ margin: "20px 0px" }}>
            Sign Up
          </BlueButton>
          <div>
            <span>
              Have an account?{" "}
              <StyledLink to="/auth/login">Log In Here</StyledLink>
            </span>
          </div>
        </form>
      </div>
    </>
  );
};

export default Register;
