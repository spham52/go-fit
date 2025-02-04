import { Box, Button, Modal, Typography } from "@mui/material";
import { useState } from "react";
import ActivitySelect from "./ActivitySelect";

const NewLogModal = () => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    boxShadow: 24,
    borderRadius: "10px",
    p: 4,
  };

  return (
    <div>
      <Button onClick={handleOpen}>New Log</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography
            id="modal-modal-title"
            variant="h6"
            component="h2"
            sx={{ my: 2 }}
          >
            New Log
          </Typography>
          <ActivitySelect />
        </Box>
      </Modal>
    </div>
  );
};

export default NewLogModal;
