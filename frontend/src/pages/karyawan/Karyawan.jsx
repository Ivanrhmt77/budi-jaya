import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProfileCard from "../../components/ProfileCard";
import KaryawanModal from '../../components/KaryawanModal';

function Karyawan() {
    const [karyawanList, setKaryawanList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedEmployeeId, setSelectedEmployeeId] = useState(null);

    useEffect(() => {
        const fetchKaryawan = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/karyawan');
                const processedKaryawan = response.data.map(karyawan => ({
                    ...karyawan,
                    fotoProfil: karyawan.fotoProfil
                        ? `../images/karyawan/${karyawan.fotoProfil}`
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

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <h2 className='pb-10'>Daftar Karyawan</h2>
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
        </div>
    );
}

export default Karyawan;