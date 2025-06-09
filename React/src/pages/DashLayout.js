import React from "react";
import { Outlet } from "react-router-dom";
import { DashBoard } from "../Components/Dashboard";

export default function DashLayout() {
  return (
    <div>
      <Outlet />
    </div>
  );
}
