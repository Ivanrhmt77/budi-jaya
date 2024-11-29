import React from 'react';

const ProfileCard = ({ href, image, name, title, email, phone }) => {
  return (
    <a 
      href={href} 
      className="w-64 bg-white text-black rounded-lg shadow-md overflow-hidden group"
    >
      <div className='overflow-hidden'>
        <div 
          className="w-full h-72 bg-cover bg-center transition-transform duration-300 ease-in-out group-hover:scale-110" 
          style={{ backgroundImage: `url(${image})` }}
        />
      </div>
      <div className="p-4 text-center">
        <h2 className="text-xl font-bold">{name}</h2>
        <p className="text-gray-500 text-sm">{title}</p>
        <div className="flex items-center mt-4">
          <div className="flex-shrink-0 mr-2">
            <svg className="h-5 w-5 text-gray-400" viewBox="0 0 20 20" fill="currentColor">
              <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z" />
              <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z" />
            </svg>
          </div>
          <a href={`mailto:${email}`} className="text-blue-500 hover:text-blue-600">
            {email}
          </a>
        </div>
        <div className="flex items-center mt-2">
          <div className="flex-shrink-0 mr-2">
            <svg className="h-5 w-5 text-gray-400" viewBox="0 0 20 20" fill="currentColor">
              <path d="M2 3a1 1 0 011-1h2.153a1 1 0 01.986.836l.74 4.435a1 1 0 01-.54 1.06l-1.548.773a11.037 11.037 0 006.105 6.105l.774-1.548a1 1 0 011.059-.54l4.435.74a1 1 0 01.836.986V17a1 1 0 01-1 1h-2C7.82 18 2 12.18 2 5V3z" />
            </svg>
          </div>
          <a href={`tel:${phone}`} className="text-blue-500 hover:text-blue-600">
            {phone}
          </a>
        </div>
      </div>
    </a>
  );
};

export default ProfileCard;