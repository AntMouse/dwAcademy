import React from 'react';

function ProductForm({ form, handleChange, handleSubmit, errors, editingProduct }) {
  return (
    <form onSubmit={handleSubmit} className="mb-4">
      <div className="mb-3">
        <input
          type="text"
          name="productType"
          className="form-control"
          placeholder="상품 유형"
          value={form.productType}
          onChange={handleChange}
        />
        {errors.productType && <div className="text-danger">{errors.productType}</div>}
      </div>
      <div className="mb-3">
        <input
          type="text"
          name="productName"
          className="form-control"
          placeholder="상품 이름"
          value={form.productName}
          onChange={handleChange}
        />
        {errors.productName && <div className="text-danger">{errors.productName}</div>}
      </div>
      <div className="mb-3">
        <input
          type="number"
          name="price"
          className="form-control"
          placeholder="가격"
          value={form.price}
          onChange={handleChange}
        />
        {errors.price && <div className="text-danger">{errors.price}</div>}
      </div>
      <div className="mb-3">
        <textarea
          name="explanation"
          className="form-control"
          placeholder="설명"
          value={form.explanation}
          onChange={handleChange}
        />
      </div>
      <button type="submit" className="btn btn-primary">
        {editingProduct ? '수정' : '생성'}
      </button>
    </form>
  );
}

export default ProductForm;
