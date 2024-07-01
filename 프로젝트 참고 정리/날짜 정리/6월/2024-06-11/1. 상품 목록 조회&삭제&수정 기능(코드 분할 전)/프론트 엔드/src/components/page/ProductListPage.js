import React, { useState, useEffect } from 'react';
import './ProductListPage.css';
import axios from 'axios';
import FilterControls from './FilterControls';

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
    fetchProductTypes();
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/products`);
      const productsWithMainType = await Promise.all(response.data.map(async (product) => {
        const mainType = await fetchMainType(product.productType);
        return { ...product, mainType };
      }));
      setProducts(productsWithMainType);
      setDisplayedProductCount(productsWithMainType.length);
    } catch (error) {
      setError('상품을 불러오는 도중 오류가 발생했습니다.');
      alert('상품을 불러오는 도중 오류가 발생했습니다.');
    }
  };

  const fetchProductTypes = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/product-types`);
      const sortedProductTypes = response.data.sort((a, b) => a.productType.localeCompare(b.productType));
      setProductTypes(sortedProductTypes);
    } catch (error) {
      setError('상품 타입을 불러오는 도중 오류가 발생했습니다.');
      alert('상품 타입을 불러오는 도중 오류가 발생했습니다.');
    }
  };

  const fetchMainType = async (subType) => {
    try {
      const response = await axios.get(`${apiUrl}/api/product-types`);
      const productType = response.data.find(pt => pt.productType === subType);
      return productType ? productType.parentType : '알 수 없음';
    } catch (error) {
      console.error('메인 타입을 불러오는 도중 오류가 발생했습니다.', error);
      return '알 수 없음';
    }
  };

  const handleEditClick = (productId) => {
    const productToEdit = products.find(product => product.id === productId);
    setEditProductData({ ...productToEdit });  // 현재 값을 임시 저장
    setEditProductId(productId);
  };

  const handleInputChange = (event, key) => {
    const { value } = event.target;
    setEditProductData(prevState => ({
      ...prevState,
      [key]: value
    }));

    if (key === 'mainType') {
      setEditProductData(prevState => ({
        ...prevState,
        productType: ''  // 메인 타입 변경 시 서브 타입 초기화
      }));
    }
  };

  const handleKeyDown = (event) => {
    const { key } = event;
    const isControlKey = key === 'Backspace' || key === 'Delete' || key === 'ArrowLeft' || key === 'ArrowRight' || key === 'Tab';
    if (!/^\d$/.test(key) && !isControlKey) {
      event.preventDefault();
    }
  };

  const handleSaveClick = async () => {
    // 유효성 검사
    if (!editProductData.productName) {
      alert('이름 칸을 입력해야 합니다.');
      return;
    }
    if (isNaN(editProductData.price) || editProductData.price === '') {
      alert('가격 칸에는 숫자만 입력해야 합니다.');
      return;
    }
    if (!editProductData.mainType || editProductData.mainType === '선택하세요') {
      alert('메인 타입을 선택해야 합니다.');
      return;
    }
    if (!editProductData.productType || editProductData.productType === '선택하세요') {
      alert('서브 타입을 선택해야 합니다.');
      return;
    }
    if (!editProductData.imageUrl) {
      alert('이미지 URL 칸을 입력해야 합니다.');
      return;
    }
    if (!editProductData.explanation) {
      alert('설명 칸을 입력해야 합니다.');
      return;
    }

    try {
      const productToUpdate = { ...editProductData };
      await axios.put(`${apiUrl}/api/products/${editProductId}`, productToUpdate);
      const updatedProducts = products.map(product => 
        product.id === editProductId ? productToUpdate : product
      );
      setProducts(updatedProducts);
      setEditProductId(null);
      setEditProductData(null);  // 임시 저장된 값 초기화
      setDisplayedProductCount(filteredProducts(updatedProducts).length);  // 필터링된 상품 수 업데이트
      alert('성공적으로 저장되었습니다.');
    } catch (error) {
      alert('상품 저장 중 오류가 발생했습니다.');
    }
  };

  const handleCancelClick = () => {
    setEditProductId(null);
    setEditProductData(null);  // 임시 저장된 값 초기화
  };

  const handleDeleteClick = async (productId) => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      try {
        await axios.delete(`${apiUrl}/api/products/${productId}`);
        const updatedProducts = products.filter(product => product.id !== productId);
        setProducts(updatedProducts);
        setDisplayedProductCount(filteredProducts(updatedProducts).length);
        alert('성공적으로 삭제되었습니다.');
      } catch (error) {
        alert('상품 삭제 중 오류가 발생했습니다.');
      }
    }
  };

  const handleParentTypeChange = (event) => {
    setSelectedParentType(event.target.value);
    setSelectedSubType('');
    setShowProducts(false);
  };

  const handleSubTypeChange = (event) => {
    setSelectedSubType(event.target.value);
    setShowProducts(false);
  };

  const handleShowProductsClick = () => {
    setShowProducts(true);
    setDisplayedProductCount(filteredProducts().length);
    setCurrentPage(1); // 페이지를 처음으로 리셋
    setSortCriteria('default'); // 전체 리스트에 대한 정렬 기준 초기화
    setSortDirection('asc'); // 정렬 방향 초기화
    setLocalSortCriteria('default'); // 현재 페이지 정렬 기준 초기화
    setLocalSortDirection('asc'); // 현재 페이지 정렬 방향 초기화
  };

  const handleSortChange = (criteria) => {
    if (currentPage === 1) {
      // 첫 페이지에서 정렬 변경 시 전체 리스트에 대해 정렬
      if (sortCriteria === criteria) {
        setSortDirection(sortDirection === 'asc' ? 'desc' : 'asc');
      } else {
        setSortCriteria(criteria);
        setSortDirection('asc');
      }
    } else {
      // 그 외의 경우 현재 페이지에 대해서만 정렬
      if (localSortCriteria === criteria) {
        setLocalSortDirection(localSortDirection === 'asc' ? 'desc' : 'asc');
      } else {
        setLocalSortCriteria(criteria);
        setLocalSortDirection('asc');
      }
    }
  };

  const handleProductsPerPageChange = (event) => {
    setProductsPerPage(Number(event.target.value));
    setCurrentPage(1); // 페이지를 처음으로 리셋
  };

  const handlePageInputChange = (event) => {
    setPageInput(event.target.value);
  };

  const handlePageInputKeyDown = (event) => {
    if (event.key === 'Enter') {
      handlePageChange();
    }
  };

  const handlePageChange = () => {
    const pageNumber = Number(pageInput);
    if (!isNaN(pageNumber) && pageNumber >= 1 && pageNumber <= totalPages) {
      setCurrentPage(pageNumber);
    } else {
      alert('유효한 페이지 번호를 입력하세요.');
      setPageInput(currentPage.toString());
    }
  };

  const getSortedProducts = (productsToSort, criteria, direction) => {
    const sortedProducts = [...productsToSort];
    const sortDirectionMultiplier = direction === 'asc' ? 1 : -1;

    switch (criteria) {
      case 'name':
        sortedProducts.sort((a, b) => a.productName.localeCompare(b.productName) * sortDirectionMultiplier);
        break;
      case 'price':
        sortedProducts.sort((a, b) => (a.price - b.price) * sortDirectionMultiplier);
        break;
      case 'mainType':
        sortedProducts.sort((a, b) => a.mainType.localeCompare(b.mainType) * sortDirectionMultiplier);
        break;
      case 'subType':
        sortedProducts.sort((a, b) => a.productType.localeCompare(b.productType) * sortDirectionMultiplier);
        break;
      case 'default':
      default:
        break;
    }
    return sortedProducts;
  };

  const filteredProducts = (productsToFilter = products) => {
    let filtered = productsToFilter;
    if (selectedParentType !== '전체보기' && selectedParentType !== '') {
      filtered = filtered.filter(product => product.mainType === selectedParentType);
    }
    if (selectedSubType !== '전체보기' && selectedSubType !== '') {
      filtered = filtered.filter(product => product.productType === selectedSubType);
    }
    return getSortedProducts(filtered, sortCriteria, sortDirection);
  };

  const uniqueParentTypes = Array.from(new Set(productTypes.map(item => item.parentType))).sort();

  // 페이지네이션 적용
  const indexOfLastProduct = currentPage * productsPerPage;
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
  let currentProducts = filteredProducts().slice(indexOfFirstProduct, indexOfLastProduct);

  // 현재 페이지에 대한 정렬 적용
  currentProducts = getSortedProducts(currentProducts, localSortCriteria, localSortDirection);

  const totalPages = Math.ceil(filteredProducts().length / productsPerPage);

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
        onParentTypeChange={handleParentTypeChange}
        onSubTypeChange={handleSubTypeChange}
        onShowProductsClick={handleShowProductsClick}
      />
      <div className="sort-controls">
        <button onClick={() => handleSortChange('default')}>기존 순서</button>
        <button onClick={() => handleSortChange('name')}>이름 순서</button>
        <button onClick={() => handleSortChange('price')}>가격 순서</button>
        <button onClick={() => handleSortChange('mainType')}>메인 타입 순서</button>
        <button onClick={() => handleSortChange('subType')}>서브 타입 순서</button>
      </div>
      <div className="pagination-controls">
        <label>페이지 당 상품 수: </label>
        <select value={productsPerPage} onChange={handleProductsPerPageChange}>
          <option value={5}>5</option>
          <option value={10}>10</option>
          <option value={20}>20</option>
          <option value={50}>50</option>
          <option value={filteredProducts().length}>전체보기</option>
        </select>
      </div>
      {showProducts && (
        <>
          <div className="product-count">
            현재 표시된 상품 수: {filteredProducts().length}개
          </div>
          <ul className="product-list">
            {currentProducts.map(product => (
              <li key={product.id} className="product-item">
                <div className="product-field">
                  {editProductId === product.id ? <label>이름</label> : null}
                  {editProductId === product.id ? (
                    <input type="text" className="product-input" value={editProductData.productName} onChange={(e) => handleInputChange(e, 'productName')} />
                  ) : (
                    <div className="product-name">{product.productName}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>가격</label> : null}
                  {editProductId === product.id ? (
                    <input type="number" className="product-input" value={editProductData.price} onKeyDown={handleKeyDown} onChange={(e) => handleInputChange(e, 'price')} />
                  ) : (
                    <div className="product-price">가격: {`${product.price.toLocaleString()}원`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>메인 타입</label> : null}
                  {editProductId === product.id ? (
                    <select
                      className="product-input"
                      value={editProductData.mainType}
                      onChange={(e) => handleInputChange(e, 'mainType')}
                    >
                      <option value="">선택하세요</option>
                      {uniqueParentTypes.map((parentType) => (
                        <option key={parentType} value={parentType}>
                          {parentType}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <div className="product-main-type">{`메인 타입: ${product.mainType}`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>서브 타입</label> : null}
                  {editProductId === product.id ? (
                    <select
                      className={`product-input ${!editProductData.mainType ? 'disabled' : ''}`}
                      value={editProductData.productType}
                      onChange={(e) => handleInputChange(e, 'productType')}
                      disabled={!editProductData.mainType}
                    >
                      <option value="">선택하세요</option>
                      {productTypes
                        .filter(subType => subType.parentType === editProductData.mainType)
                        .map(subType => (
                          <option key={subType.productType} value={subType.productType}>
                            {subType.productType}
                          </option>
                        ))}
                    </select>
                  ) : (
                    <div className="product-sub-type">{`서브 타입: ${product.productType}`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>이미지 URL</label> : null}
                  {editProductId === product.id ? (
                    <input
                      type="text"
                      className="product-input"
                      value={editProductData.imageUrl}
                      onChange={(e) => handleInputChange(e, 'imageUrl')}
                    />
                  ) : (
                    <div className="product-imageUrl"><img src={product.imageUrl} alt="상품 이미지" className="product-image"/></div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>설명</label> : null}
                  {editProductId === product.id ? (
                    <textarea
                      className="product-input product-textarea"
                      value={editProductData.explanation}
                      onChange={(e) => handleInputChange(e, 'explanation')}
                    />
                  ) : (
                    <div className="product-explanation">{`상품설명: ${product.explanation}`}</div>
                  )}
                </div>
                <div className="product-edit-buttons">
                  {editProductId === product.id ? (
                    <>
                      <button onClick={handleSaveClick}>저장</button>
                      <button onClick={handleCancelClick}>취소</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditClick(product.id)}>수정</button>
                      <button onClick={() => handleDeleteClick(product.id)}>삭제</button>
                    </>
                  )}
                </div>
              </li>
            ))}
          </ul>
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
        </>
      )}
    </div>
  );
}

export default ProductListPage;