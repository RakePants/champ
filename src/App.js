import './App.css';
import Orders from './pages/Orders';
import Applications from './pages/Applications';
import Complete from './pages/Complete';
import Header from './widgets/Header';
import {Routes, Route} from 'react-router-dom'

function App() {
  return (
    <>
      <Header></Header>
      <Routes>
        <Route path="/" element={<Applications/>} />
        <Route path="/apps" element={<Applications/>} />
        <Route path="/orders" element={<Orders/>} />
        <Route path="/complete" element={<Complete/>} />
      </Routes>
    </>
  );
}

export default App;
