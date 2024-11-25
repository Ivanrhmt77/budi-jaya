import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Navbar from './components/Navbar';

function App() {
  return (
    <Router>
      <div className="bg-zinc-900 min-h-screen w-screen mx-auto">
        <Navbar />
        <div className='bg-zinc-900 min-h-screen rounded-4xl mx-auto max-w-7xl px-2 sm:px-6 lg:px-8'></div>
        {/* <div className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/patients" element={<Patients />} />
          </Routes>
        </div>  */}
      </div>
    </Router>
  )
}

export default App
