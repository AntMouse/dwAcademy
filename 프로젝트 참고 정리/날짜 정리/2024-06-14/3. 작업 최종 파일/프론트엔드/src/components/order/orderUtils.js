// orderUtils.js
export const filteredOrders = (orders, selectedStatus) => {
  if (selectedStatus === '전체보기') {
    return orders;
  }
  return orders.filter(order => order.status === selectedStatus);
};

export const sortedOrders = (orders, selectedStatus, sortCriteria, sortDirection) => {
  const filtered = filteredOrders(orders, selectedStatus);
  if (sortCriteria === 'default') {
    return filtered;
  }
  return [...filtered].sort((a, b) => {
    if (sortCriteria === 'date') {
      const dateA = new Date(a.createDate);
      const dateB = new Date(b.createDate);
      return sortDirection === 'asc' ? dateA - dateB : dateB - dateA;
    }
    if (sortCriteria === 'totalPrice') {
      return sortDirection === 'asc' ? a.totalPrice - b.totalPrice : b.totalPrice - a.totalPrice;
    }
    if (sortCriteria === 'status') {
      return sortDirection === 'asc' ? a.status.localeCompare(b.status) : b.status.localeCompare(a.status);
    }
    return 0;
  });
};

export const getCurrentOrders = (orders, selectedStatus, sortCriteria, sortDirection, currentPage, ordersPerPage) => {
  const sorted = sortedOrders(orders, selectedStatus, sortCriteria, sortDirection);
  return sorted.slice((currentPage - 1) * ordersPerPage, currentPage * ordersPerPage);
};
