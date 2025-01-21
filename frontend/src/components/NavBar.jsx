import { AppBar, Container, Toolbar } from "@mui/material";
import { Link } from "react-router-dom";
import styled from "styled-components";

const NavBarStyled = styled.div`
  display: flex;
  width: 100%;
  height: 80px;
  align-items: center;
  position: fixed;
  top: 0;
  box-sizing: border-box;
  padding: 20px;
  border-bottom: 1px solid rgb(76, 88, 91, 0.2);
`;

const NavBarItem = styled(Link)`
  text-decoration: none;
  color: black;

  &:hover {
    color: black;
    opacity: 0.4;
  }
`;

const Logo = styled.h1`
  margin: 20px;
  padding-right: 30px;
`;

const NavBar = () => {
  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <div>hi</div>
          <div>ho</div>
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default NavBar;
