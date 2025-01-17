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
