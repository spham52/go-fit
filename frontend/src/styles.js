import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import styled from "styled-components";

export const BlueButton = styled(Button)`
  &.MuiButton-root {
    border-radius: 20px;
    background: rgb(126, 153, 163);
  }
`;

export const StyledLink = styled(Link)`
  color: #1876d2;
  text-decoration: none;

  a:visited {
    color: #1876d2;
  }
`;

export const AuthBox = styled.div`
  width: 350px;
  display: flex;
  flex-direction: column;
  background-color: rgba(255, 255, 255, 0.756);
  border-radius: 10px;
  backdrop-filter: blur(10px);
  padding: 40px;
  box-sizing: border-box;
  box-shadow: 0px 15px 25px rgba(56, 56, 56, 0.5);
`;

export const AuthPage = styled.div`
  display: flex;
  width: 100vw;
  height: 100vh;
  justify-content: center;
  align-items: center;
  color: rgb(41, 41, 41);
  font-size: 0.9em;
  background: rgb(126, 153, 163);
  background: linear-gradient(
    90deg,
    rgba(126, 153, 163, 1) 0%,
    rgba(165, 191, 204, 1) 0%,
    rgba(244, 237, 211, 1) 100%
  );
`;
