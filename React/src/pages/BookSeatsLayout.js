import React from "react";
import { Outlet } from "react-router-dom";

export default function BookSeatsLayout() {
  return (
    <div>
      <Outlet />
    </div>
  );
}
