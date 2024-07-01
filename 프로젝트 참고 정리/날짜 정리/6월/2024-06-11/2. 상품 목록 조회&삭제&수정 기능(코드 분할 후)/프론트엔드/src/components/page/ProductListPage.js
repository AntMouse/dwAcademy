import React, { useState, useEffect } from 'react';
import './ProductListPage.css';
import { fetchProducts, fetchProductTypes } from './productListPageApi';
import { showError, errorMessages } from './messages';
import FilterControls from './FilterControls';
import { SortControls, handleSortChange, getSortedProducts, filteredProducts } from './SortUtilsFull';
import { Pagination, PaginationControls, handleProductsPerPageChange, handlePageInputChange, handlePageInputKeyDown, handlePageChange } from './PaginationFull';
import ProductList from './ProductList';
import { handleSaveClick, handleCancelClick, handleDeleteClick, handleEditClick, handleInputChange, handleKeyDown, handleShowProductsClick, handleParentTypeChange, handleSubTypeChange } from './productHandlers';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [productTypes, setProductTypes] = useState([]);
  const [selectedParentType, setSelectedParentType] = useState('');
  const [selectedSubType, setSelectedSubType] = useState('');
  const [editProductId, setEditProductId] = useState(null);
  const [editProductData, setEditProductData] = useState(null);
  const [error, setError] = useState('');
  const [showProducts, setShowProducts] = useState(false);
  const [displayedProductCount, setDisplayedProductCount] = useState(0);
  const [sortCriteria, setSortCriteria] = useState('default');
  const [sortDirection, setSortDirection] = useState('asc');
  const [currentPage, setCurrentPage] = useState(1);
  const [productsPerPage, setProductsPerPage] = useState(20);
  const [pageInput, setPageInput] = useState('1');
  const [localSortCriteria, setLocalSortCriteria] = useState('default');
  const [localSortDirection, setLocalSortDirection] = useState('asc');

  useEffect(() => {
    fetchProductTypes().then(setProductTypes).catch(() => {
      showError(errorMessages.fetchProductTypes);
    });
    fetchProducts().then(setProducts).catch(() => {
      showError(errorMessages.fetchProducts);
    });
  }, []);

  const handleEditClickWrapper = (productId) => {
    handleEditClick(productId, products, setEditProductData, setEditProductId);
  };

  const handleInputChangeWrapper = (event, key) => {
    handleInputChange(event, key, setEditProductData);
  };

  const handleShowProductsClickWrapper = () => {
    handleShowProductsClick(setProducts, setShowProducts, setDisplayedProductCount, setCurrentPage, setSortCriteria, setSortDirection, setLocalSortCriteria, setLocalSortDirection);
    setEditProductId(null);
    setEditProductData(null);
  };

  const handleParentTypeChangeWrapper = (event) => {
    handleParentTypeChange(event, setSelectedParentType, setSelectedSubType, setShowProducts);
    setEditProductId(null);
    setEditProductData(null);
  };

  const handleSubTypeChangeWrapper = (event) => {
    handleSubTypeChange(event, setSelectedSubType, setShowProducts);
    setEditProductId(null);
    setEditProductData(null);
  };

  const handleSortChangeWrapper = (criteria) => {
    handleSortChange(criteria, currentPage, sortCriteria, sortDirection, setSortCriteria, setSortDirection, localSortCriteria, setLocalSortCriteria, localSortDirection, setLocalSortDirection);
  };

  const handleSaveClickWrapper = () => {
    handleSaveClick(editProductData, editProductId, products, setProducts, setEditProductId, setEditProductData, setDisplayedProductCount);
  };

  const handleCancelClickWrapper = () => {
    handleCancelClick(setEditProductId, setEditProductData);
  };

  const handleDeleteClickWrapper = (productId) => {
    handleDeleteClick(productId, products, setProducts, setDisplayedProductCount);
  };

  const filteredProductsWrapper = (productsToFilter = products) => {
    return filteredProducts(productsToFilter, selectedParentType, selectedSubType, sortCriteria, sortDirection);
  };

  const uniqueParentTypes = Array.from(new Set(productTypes.map(item => item.parentType))).sort();

  const indexOfLastProduct = currentPage * productsPerPage;
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
  let currentProducts = filteredProductsWrapper().slice(indexOfFirstProduct, indexOfLastProduct);

  currentProducts = getSortedProducts(currentProducts, localSortCriteria, localSortDirection);

  const totalPages = Math.ceil(filteredProductsWrapper().length / productsPerPage);

  useEffect(() => {
    setPageInput(currentPage.toString());
  }, [currentPage]);

  return (
    <div className="product-list-container">
      <h1>Logo 사진파일 들어갈 예정입니다.(사진이 집컴에 없음)</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <FilterControls
        productTypes={productTypes}
        selectedParentType={selectedParentType}
        selectedSubType={selectedSubType}
        onParentTypeChange={handleParentTypeChangeWrapper}
        onSubTypeChange={handleSubTypeChangeWrapper}
        onShowProductsClick={handleShowProductsClickWrapper}
      />
      <SortControls handleSortChange={handleSortChangeWrapper} />
      {showProducts && (
        <>
          <div className="product-count">
            현재 표시된 상품 수: {filteredProductsWrapper().length}개
          </div>
          <PaginationControls 
            productsPerPage={productsPerPage} 
            handleProductsPerPageChange={(e) => handleProductsPerPageChange(e, setProductsPerPage, setCurrentPage)} 
            totalProducts={filteredProductsWrapper().length} 
          />
          <ProductList
            currentProducts={currentProducts}
            editProductId={editProductId}
            editProductData={editProductData}
            handleInputChange={handleInputChangeWrapper}
            handleKeyDown={handleKeyDown}
            uniqueParentTypes={uniqueParentTypes}
            productTypes={productTypes}
            handleSaveClickWrapper={handleSaveClickWrapper}
            handleCancelClickWrapper={handleCancelClickWrapper}
            handleEditClick={handleEditClickWrapper}
            handleDeleteClickWrapper={handleDeleteClickWrapper}
          />
          <Pagination
            currentPage={currentPage}
            totalPages={totalPages}
            handlePageChange={() => handlePageChange(pageInput, totalPages, setCurrentPage, setPageInput, currentPage)}
            handlePageInputChange={(e) => handlePageInputChange(e, setPageInput)}
            handlePageInputKeyDown={(e) => handlePageInputKeyDown(e, () => handlePageChange(pageInput, totalPages, setCurrentPage, setPageInput, currentPage))}
            pageInput={pageInput}
            setCurrentPage={setCurrentPage}
          />
        </>
      )}
    </div>
  );
}

export default ProductListPage;
