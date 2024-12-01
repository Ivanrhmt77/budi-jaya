import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AddKaryawanModal({ isOpen, onClose, onAddKaryawan }) {
    const [formData, setFormData] = useState({
        namaLengkap: '',
        email: '',
        nomorTelepon: '',
        alamat: '',
        tanggalLahir: '',
        jabatan: '',
        statusKaryawan: ''
    });

    const [departments, setDepartments] = useState([]);
    const [positions, setPositions] = useState([]);

    useEffect(() => {
        const fetchDepartmentsAndPositions = async () => {
            try {
                const [departmentResponse, positionResponse] = await Promise.all([
                    axios.get('http://localhost:8080/api/departemen'),
                    axios.get('http://localhost:8080/api/jabatan')
                ]);
                setDepartments(departmentResponse.data);
                setPositions(positionResponse.data);
            } catch (error) {
                console.error('Error fetching departments or positions:', error);
            }
        };

        if (isOpen) {
            fetchDepartmentsAndPositions();
        }
    }, [isOpen]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
    
        const updatedValue = name === "jabatan" ? Number(value) : value;
    
        setFormData(prev => ({
            ...prev,
            [name]: updatedValue
        }));
    };    

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Submitting data:', formData);


        try {
            const response = await axios.post('http://localhost:8080/api/karyawan', formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            onAddKaryawan(response.data);

            setFormData({
                namaLengkap: '',
                email: '',
                nomorTelepon: '',
                alamat: '',
                tanggalLahir: '',
                jabatan: '',
                statusKaryawan: ''
            });
            onClose();
        } catch (error) {
            console.error('Error adding karyawan:', error.response || error);
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-2xl shadow-2xl w-full max-w-4xl max-h-[90vh] overflow-auto relative p-8">
                {/* Close Button */}
                <button 
                    onClick={onClose} 
                    className="absolute top-4 right-4 text-red-600 bg-white border border-gray-400 hover:bg-gray-200 z-60"
                >
                    <svg className="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                    </svg>
                </button>

                <h2 className="text-2xl font-bold mb-6 text-gray-800">Tambah Karyawan Baru</h2>

                <form onSubmit={handleSubmit} className="grid grid-cols-2 gap-6">
                    {/* Nama Lengkap */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Nama Lengkap</label>
                        <input 
                            type="text" 
                            name="namaLengkap"
                            value={formData.namaLengkap}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>

                    {/* Email */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Email</label>
                        <input 
                            type="email" 
                            name="email"
                            value={formData.email}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>

                    {/* Nomor Telepon */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Nomor Telepon</label>
                        <input 
                            type="tel" 
                            name="nomorTelepon"
                            value={formData.nomorTelepon}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>

                    {/* Alamat */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Alamat</label>
                        <input 
                            type="text" 
                            name="alamat"
                            value={formData.alamat}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>

                    {/* Tanggal Lahir */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Tanggal Lahir</label>
                        <input 
                            type="date" 
                            name="tanggalLahir"
                            value={formData.tanggalLahir}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>

                    {/* Jabatan */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Jabatan</label>
                        <select 
                            name="jabatan"
                            value={formData.jabatan}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        >
                            <option value="">Pilih Jabatan</option>
                            {positions.map(pos => (
                                <option key={pos.id} value={pos.id}>
                                    {pos.namaJabatan}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Status Karyawan */}
                    <div>
                        <label className="block text-gray-700 text-sm font-bold mb-2">Status Karyawan</label>
                        <select 
                            name="statusKaryawan"
                            value={formData.statusKaryawan}
                            onChange={handleInputChange}
                            required 
                            className="shadow appearance-none bg-gray-100 border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        >
                            <option value="">Pilih Status</option>
                            <option value="AKTIF">Aktif</option>
                            <option value="NONAKTIF">Tidak Aktif</option>
                        </select>
                    </div>

                    {/* Submit Button */}
                    <div className="col-span-2 flex justify-end mt-6">
                        <button 
                            type="submit" 
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        >
                            Tambah Karyawan
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default AddKaryawanModal;
