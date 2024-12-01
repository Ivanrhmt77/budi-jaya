import React, { useState, useEffect } from 'react';
import axios from 'axios';

function KaryawanModal({ isOpen, onClose, employeeId }) {
    const [karyawan, setKaryawan] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (isOpen && employeeId) {
            const fetchKaryawan = async () => {
                try {
                    setLoading(true);
                    const response = await axios.get(`http://localhost:8080/api/karyawan?id=${employeeId}`);
                    const data = response.data[0] || {}; 
                    setKaryawan(data);
                    setLoading(false);
                } catch (err) {
                    console.error('Error fetching employee details:', err);
                    setError('Failed to fetch employee details');
                    setLoading(false);
                }
            };

            fetchKaryawan();
        }
    }, [isOpen, employeeId]);

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-2xl shadow-2xl w-full max-w-4xl max-h-[90vh] overflow-auto relative">
                {/* Close Button */}
                <button 
                    onClick={onClose} 
                    className="absolute top-4 right-4 text-red-600 bg-white border border-gray-400 hover:bg-gray-200 z-60"
                >
                    <svg className="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                    </svg>
                </button>

                {loading ? (
                    <div className="flex justify-center items-center h-64">
                        <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-blue-500"></div>
                    </div>
                ) : error ? (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded" role="alert">
                        {error}
                    </div>
                ) : karyawan ? (
                    <div className="flex">
                        {/* Profile Image Section */}
                        <div className="w-1/3 bg-blue-50 p-8 flex flex-col items-center justify-center">
                            <div 
                                className="w-64 h-64 rounded-full border-4 border-blue-300 bg-cover bg-center mb-6 shadow-lg" 
                                style={{ 
                                    backgroundImage: `url(/images/karyawan/${karyawan.fotoProfil})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center'
                                }}
                            />
                            <h2 className="text-2xl font-bold text-gray-800 text-center mb-2">{karyawan.namaLengkap}</h2>
                            <p className="text-gray-500 text-sm">{karyawan.jabatan.namaJabatan}</p>
                        </div>

                        {/* Employee Details Section */}
                        <div className="w-2/3 p-8">
                            <div className="grid grid-cols-2 gap-6">
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Email</p>
                                        <a href={`mailto:${karyawan.email}`} className="text-blue-600 hover:underline">
                                            {karyawan.email}
                                        </a>
                                    </div>
                                </div>
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Nomor Telepon</p>
                                        <a href={`tel:${karyawan.nomorTelepon}`} className="text-green-600 hover:underline">
                                            {karyawan.nomorTelepon}
                                        </a>
                                    </div>
                                </div>
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-purple-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h3a1 1 0 011 1v5m-4 0h4" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Departemen</p>
                                        <p className="text-gray-700">{karyawan.departemen.namaDepartemen}</p>
                                    </div>
                                </div>
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Alamat</p>
                                        <p className="text-gray-700">{karyawan.alamat}</p>
                                    </div>
                                </div>
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-orange-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Tanggal Lahir</p>
                                        <p className="text-gray-700">{karyawan.tanggalLahir}</p>
                                    </div>
                                </div>
                                <div className="flex items-center">
                                    <svg className="w-6 h-6 mr-3 text-teal-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Tanggal Masuk</p>
                                        <p className="text-gray-700">{karyawan.tanggalMasuk}</p>
                                    </div>
                                </div>
                                <div className="flex items-center col-span-2">
                                    <svg className="w-6 h-6 mr-3 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                    </svg>
                                    <div>
                                        <p className="text-gray-500 text-sm">Status</p>
                                        <p className="text-gray-700">{karyawan.statusKaryawan}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                ) : null}
            </div>
        </div>
    );
}

export default KaryawanModal;