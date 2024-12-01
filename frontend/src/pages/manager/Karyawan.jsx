import React, { useState, useEffect } from 'react'; 
import axios from 'axios'; 
import ProfileCard from "../../components/ProfileCard"; 
import KaryawanModal from '../../components/KaryawanModal';
import AddKaryawanModal from '../../components/AddKaryawanModal';  // Import the new modal

function Karyawan() {
    const [karyawanList, setKaryawanList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedEmployeeId, setSelectedEmployeeId] = useState(null);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);  // New state for add modal

    useEffect(() => {
        const fetchKaryawan = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/karyawan');
                const processedKaryawan = response.data.map(karyawan => ({
                    ...karyawan,
                    fotoProfil: karyawan.fotoProfil
                        ? `/images/karyawan/${karyawan.fotoProfil}`
                        : '/profile.jpg'
                }));
                setKaryawanList(processedKaryawan);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching data:', err);
                setError('Failed to fetch employee data');
                setLoading(false);
            }
        };

        fetchKaryawan();
    }, []);

    const handleProfileClick = (employeeId) => {
        setSelectedEmployeeId(employeeId);
    };

    const handleCloseModal = () => {
        setSelectedEmployeeId(null);
    };

    const handleOpenAddModal = () => {
        setIsAddModalOpen(true);
    };

    const handleCloseAddModal = () => {
        setIsAddModalOpen(false);
    };

    const handleAddKaryawan = (newKaryawan) => {
        // Update the list with the new employee
        const newKaryawanWithImage = {
            ...newKaryawan,
            fotoProfil: newKaryawan.fotoProfil 
                ? `/images/karyawan/${newKaryawan.fotoProfil}`
                : '/profile.jpg'
        };
        setKaryawanList(prev => [...prev, newKaryawanWithImage]);
    };

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <div className='flex pb-10 justify-between'>
                <h2>Daftar Karyawan</h2>
                <button
                    className='bg-green-600 font-bold hover:bg-green-700'
                    onClick={handleOpenAddModal}
                >+ Tambah Karyawan</button>
            </div>
            <div className="flex flex-wrap items-start justify-center gap-10">
                {karyawanList.map((karyawan) => (
                    <div 
                        key={karyawan.id} 
                        onClick={() => handleProfileClick(karyawan.id)}
                        className="cursor-pointer"
                    >
                        <ProfileCard
                            image={karyawan.fotoProfil}
                            name={karyawan.namaLengkap}
                            title={karyawan.jabatan?.namaJabatan || 'Employee'}
                            email={karyawan.email}
                            phone={karyawan.nomorTelepon}
                        />
                    </div>
                ))}
            </div>

            <KaryawanModal 
                isOpen={selectedEmployeeId !== null}
                onClose={handleCloseModal}
                employeeId={selectedEmployeeId}
            />

            <AddKaryawanModal 
                isOpen={isAddModalOpen}
                onClose={handleCloseAddModal}
                onAddKaryawan={handleAddKaryawan}
            />
        </div>
    );
}

export default Karyawan;