import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import KaryawanHome  from './pages/Home';
import Karyawan from './pages/Karyawan';

function App() {
  return (
    <Router>
      <div className="bg-zinc-900 w-screen mx-auto">
        <Navbar />
        <div className='container my-24 bg-zinc-900 rounded-4xl mx-auto max-w-7xl px-2 sm:px-6 lg:px-8'>
          <Routes>
            <Route path="/" element={<KaryawanHome />} />
            <Route path="/Karyawan" element={<Karyawan />} />
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App
