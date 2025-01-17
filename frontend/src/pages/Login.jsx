import "../auth.css";
import TextField from "@mui/material/TextField";
import { BlueButton, StyledLink } from "../styles";

const Login = () => {
  return (
    <>
      <div className="auth-box">
        <h1>Log In</h1>
        <form>
          <TextField label="Username" variant="outlined" required fullWidth />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            margin="normal"
            required
            fullWidth
          />
          <BlueButton fullWidth variant="contained" sx={{ margin: "20px 0px" }}>
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
      </div>
    </>
  );
};

export default Login;
