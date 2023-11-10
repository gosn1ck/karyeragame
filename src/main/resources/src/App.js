// import "./style/style.css"

import {BrowserRouter, Route, Routes} from "react-router-dom";

import Home from "./pages/home/Home";
import Profile from "./pages/account/Profile";


function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="profile" element={<Profile />} />
        </Routes>
      </BrowserRouter>
    // <div className="app">
    //
    // <Home />
    //
    // </div>
  );
}

export default App;

