// src/components/DepartemenList.js
import React, { useEffect, useState } from "react";

const DepartemenList = () => {
  const [departemen, setDepartemen] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchDepartemen = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/departemen");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setDepartemen(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchDepartemen();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Daftar Departemen</h1>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">ID</th>
            <th className="py-2 px-4 border-b">Nama Departemen</th>
          </tr>
        </thead>
        <tbody>
          {departemen.map((dept) => (
            <tr key={dept.id}>
              <td className="py-2 px-4 border-b">{dept.id}</td>
              <td className="py-2 px-4 border-b">{dept.namaDepartemen}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DepartemenList;
