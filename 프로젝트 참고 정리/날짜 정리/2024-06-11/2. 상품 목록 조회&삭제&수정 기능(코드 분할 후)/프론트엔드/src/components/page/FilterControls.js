import React from 'react';

function FilterControls({
  productTypes,
  selectedParentType,
  selectedSubType,
  onParentTypeChange,
  onSubTypeChange,
  onShowProductsClick
}) {
  const uniqueParentTypes = Array.from(new Set(productTypes.map(pt => pt.parentType))).sort();
  const filteredSubTypes = productTypes.filter(pt => pt.parentType === selectedParentType).sort((a, b) => a.productType.localeCompare(b.productType));

  return (
    <div className="filter-container">
      <select 
        value={selectedParentType} 
        onChange={onParentTypeChange} 
        className="filter-select"
      >
        <option value="">메인 타입 선택</option>
        <option value="전체보기">전체보기</option>
        {uniqueParentTypes.map(parentType => (
          <option key={parentType} value={parentType}>{parentType}</option>
        ))}
      </select>
      <select 
        value={selectedSubType} 
        onChange={onSubTypeChange} 
        disabled={!selectedParentType || selectedParentType === '전체보기'}
        className={`filter-select ${selectedParentType && selectedParentType !== '전체보기' ? "enabled" : ""}`}
      >
        <option value="">서브 타입 선택</option>
        {selectedParentType && selectedParentType !== '전체보기' && (
          <option value="전체보기">전체보기</option>
        )}
        {filteredSubTypes.map(subType => (
          <option key={subType.productType} value={subType.productType}>{subType.productType}</option>
        ))}
      </select>
      <button 
        onClick={onShowProductsClick} 
        className="filter-button" 
        disabled={!selectedParentType || (!selectedSubType && selectedParentType !== '전체보기')}
      >
        상품 보기
      </button>
    </div>
  );
}

export default FilterControls;
