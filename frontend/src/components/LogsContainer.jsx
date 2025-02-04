import { Button, Container, Stack } from "@mui/material";
import Log from "./Log";
import { useState } from "react";
import NewLogModal from "./NewLogModal";

const LogsContainer = () => {
  return (
    <Container
      sx={{
        border: "1px solid rgb(0, 0, 0, 0.2)",
        boxSizing: "border-box",
        marginTop: "10px",
        borderRadius: "10px",
      }}
    >
      <h2>Log</h2>
      <NewLogModal />
      <Stack
        direction="column"
        spacing={1}
        sx={{
          justifyContent: "center",
          alignItems: "stretch",
        }}
      >
        <Log />
      </Stack>
    </Container>
  );
};

export default LogsContainer;
