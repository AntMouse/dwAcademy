// src/components/ProductList.js
import React from 'react';

function ProductList({
  currentProducts,
  editProductId,
  editProductData,
  handleInputChange,
  handleKeyDown,
  uniqueParentTypes,
  productTypes,
  handleSaveClickWrapper,
  handleCancelClickWrapper,
  handleEditClick,
  handleDeleteClickWrapper
}) {
  return (
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
                <button onClick={handleSaveClickWrapper}>저장</button>
                <button onClick={handleCancelClickWrapper}>취소</button>
              </>
            ) : (
              <>
                <button onClick={() => handleEditClick(product.id)}>수정</button>
                <button onClick={() => handleDeleteClickWrapper(product.id)}>삭제</button>
              </>
            )}
          </div>
        </li>
      ))}
    </ul>
  );
}

export default ProductList;
