// useFetch.js

import { useState, useEffect } from 'react';
import { getData } from '../utils/apiUtils';

export const useFetch = (endpoint, errorMessage) => {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await getData(endpoint, errorMessage);
        setData(result);
      } catch (error) {
        setError(error);
      }
    };

    fetchData();
  }, [endpoint, errorMessage]);

  return { data, error };
};
