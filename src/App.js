import './App.css';
import Orders from './pages/Orders';
import Applications from './pages/Applications';
import Complete from './pages/Complete';
import Header from './widgets/Header';
import {Routes, Route} from 'react-router-dom'
import Relocate from './pages/Relocate';

function App() {
  return (
    <>
      <Header></Header>
      <Routes>
        <Route path="/" element={<Relocate/>} />
        <Route path="/apps/:id" element={<Applications/>} />
        <Route path="/orders/:id" element={<Orders/>} />
        <Route path="/complete/:id" element={<Complete/>} />
        <Route path="*" element={<Applications/>} />
      </Routes>
    </>
  );
}

export default App;
