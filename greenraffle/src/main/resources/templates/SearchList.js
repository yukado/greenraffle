import React, { useState, useEffect } from 'react';

function SearchList() {
  const [data, setData] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    // Beispiel-API: Ersetze durch deine echte API
    fetch('https://api.example.com/items')
      .then(res => res.json())
      .then(json => setData(json))
      .catch(err => console.error('Fehler beim Laden:', err));
  }, []);

  const filteredData = data.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    React.createElement('div', { style: { padding: '2rem' } },
      React.createElement('h2', null, '🔍 Suche'),
      React.createElement('input', {
        type: 'text',
        placeholder: 'Suchbegriff eingeben...',
        value: searchTerm,
        onChange: e => setSearchTerm(e.target.value),
        style: {
          padding: '0.5rem',
          width: '100%',
          marginBottom: '1rem',
          fontSize: '1rem'
        }
      }),
      React.createElement('ul', null,
        filteredData.map(item =>
          React.createElement('li', { key: item.id }, item.name)
        )
      )
    )
  );
}

export default SearchList;