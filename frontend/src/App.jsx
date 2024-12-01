import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home  from './pages/karyawan/Home';
import Karyawan from './pages/karyawan/Karyawan';
import HomeManager from './pages/manager/Home';
import KaryawanManager from './pages/manager/Karyawan';


function App() {
  const [userRole, setUserRole] = useState('karyawan');

  return (
    <Router>
      <div className="bg-zinc-900 w-screen mx-auto">
        <Navbar userRole={userRole} />
        <div className='container my-24 bg-zinc-900 rounded-4xl mx-auto max-w-7xl px-2 sm:px-6 lg:px-8'>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path='/Karyawan' element={<Karyawan />} />
            <Route path='/manager' element={<HomeManager />} />
            <Route path='/manager/Karyawan' element={<KaryawanManager />} />
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App