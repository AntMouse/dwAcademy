// OrderPagination.js

import React from 'react';
import './OrderListPage.css';

function OrderPagination({ currentPage, totalPages, setCurrentPage }) {
  const maxPageButtons = 5;
  const halfMaxPageButtons = Math.floor(maxPageButtons / 2);

  let startPage = Math.max(1, currentPage - halfMaxPageButtons);
  let endPage = Math.min(totalPages, currentPage + halfMaxPageButtons);

  if (currentPage <= halfMaxPageButtons) {
    endPage = Math.min(totalPages, maxPageButtons);
  }

  if (currentPage > totalPages - halfMaxPageButtons) {
    startPage = Math.max(1, totalPages - maxPageButtons + 1);
  }

  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = startPage; i <= endPage; i++) {
      pageNumbers.push(
        <button
          key={i}
          onClick={() => setCurrentPage(i)}
          className={`order-pagination-button ${currentPage === i ? 'active' : ''}`}
        >
          {i}
        </button>
      );
    }
    return pageNumbers;
  };

  return (
    <div className="order-pagination-buttons">
      <button
        onClick={() => setCurrentPage(1)}
        disabled={currentPage === 1}
        className={currentPage === 1 ? 'disabled' : ''}
      >
        {'<<'}
      </button>
      <button
        onClick={() => setCurrentPage(currentPage - 1)}
        disabled={currentPage === 1}
        className={currentPage === 1 ? 'disabled' : ''}
      >
        {'<'}
      </button>
      {startPage > 1 && <span>...</span>}
      {renderPageNumbers()}
      {endPage < totalPages && <span>...</span>}
      <button
        onClick={() => setCurrentPage(currentPage + 1)}
        disabled={currentPage === totalPages}
        className={currentPage === totalPages ? 'disabled' : ''}
      >
        {'>'}
      </button>
      <button
        onClick={() => setCurrentPage(totalPages)}
        disabled={currentPage === totalPages}
        className={currentPage === totalPages ? 'disabled' : ''}
      >
        {'>>'}
      </button>
    </div>
  );
}

export default OrderPagination;
