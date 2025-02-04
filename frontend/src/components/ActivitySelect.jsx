import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { useState } from "react";

const activities = ["activity 1", "activity 2", "activity 3"];

const ActivitySelect = () => {
  const [activity, setActivity] = useState("");

  const handleChange = (event) => {
    setActivity(event.target.value);
  };

  return (
    <FormControl fullWidth>
      <InputLabel id="activity-select-label">Activity</InputLabel>
      <Select
        labelId="activity-select-label"
        onChange={handleChange}
        value={activity}
        label="Activity"
      >
        {activities.map((activity) => {
          return (
            <MenuItem value={activity} key={activity}>
              {activity}
            </MenuItem>
          );
        })}
      </Select>
    </FormControl>
  );
};

export default ActivitySelect;
