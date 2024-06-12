// src/components/PaginationFull.js
import React from 'react';

export function Pagination({
  currentPage,
  totalPages = 1,  // 기본값 설정
  handlePageChange,
  handlePageInputChange,
  handlePageInputKeyDown,
  pageInput,
  setCurrentPage
}) {
  return (
    <div className="pagination-buttons">
      <button 
        onClick={() => setCurrentPage(1)} 
        disabled={currentPage === 1}
        className={currentPage === 1 ? 'disabled' : ''}
      >
        처음
      </button>
      <button 
        onClick={() => setCurrentPage(currentPage - 1)} 
        disabled={currentPage === 1}
        className={currentPage === 1 ? 'disabled' : ''}
      >
        이전
      </button>
      <input
        type="number"
        value={pageInput}
        onChange={handlePageInputChange}
        onKeyDown={handlePageInputKeyDown}
        min="1"
        max={totalPages}
      />
      <button onClick={handlePageChange}>이동</button>
      <button 
        onClick={() => setCurrentPage(currentPage + 1)} 
        disabled={currentPage === totalPages}
        className={currentPage === totalPages ? 'disabled' : ''}
      >
        다음
      </button>
      <button 
        onClick={() => setCurrentPage(totalPages)} 
        disabled={currentPage === totalPages}
        className={currentPage === totalPages ? 'disabled' : ''}
      >
        마지막
      </button>
    </div>
  );
}

export function PaginationControls({ productsPerPage, handleProductsPerPageChange, totalProducts }) {
  return (
    <div className="pagination-controls">
      <label></label>
      <select value={productsPerPage} onChange={handleProductsPerPageChange}>
        <option value={5}>5</option>
        <option value={10}>10</option>
        <option value={20}>20</option>
        <option value={50}>50</option>
        <option value={totalProducts}>전체보기</option>
      </select>
    </div>
  );
}

export const handleProductsPerPageChange = (event, setProductsPerPage, setCurrentPage) => {
  setProductsPerPage(Number(event.target.value));
  setCurrentPage(1);
};

export const handlePageInputChange = (event, setPageInput) => {
  setPageInput(event.target.value);
};

export const handlePageInputKeyDown = (event, handlePageChange) => {
  if (event.key === 'Enter') {
    handlePageChange();
  }
};

export const handlePageChange = (pageInput, totalPages, setCurrentPage, setPageInput, currentPage) => {
  const pageNumber = Number(pageInput);
  if (!isNaN(pageNumber) && pageNumber >= 1 && pageNumber <= totalPages) {
    setCurrentPage(pageNumber);
  } else {
    alert('유효한 페이지 번호를 입력하세요.');
    setPageInput(currentPage.toString());
  }
};
