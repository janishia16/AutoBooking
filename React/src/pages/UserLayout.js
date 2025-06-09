import React from "react";
import { Outlet } from "react-router-dom";

// child routes to be rendered inside a parent route.
export default function UserLayout() {
  return (
    <div>
      <Outlet /> 
    </div>
  );
}
