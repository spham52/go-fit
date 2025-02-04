import { Container } from "@mui/material";
import LogsContainer from "../components/LogsContainer";
import NavBar from "../components/NavBar";

const Dashboard = () => {
  return (
    <>
      <NavBar />
      <Container>
        <LogsContainer />
      </Container>
    </>
  );
};

export default Dashboard;
