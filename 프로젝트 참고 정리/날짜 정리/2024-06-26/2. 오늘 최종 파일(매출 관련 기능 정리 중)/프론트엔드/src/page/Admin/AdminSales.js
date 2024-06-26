import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ReactPaginate from 'react-paginate';

const AdminSales = () => {
    const [parentTypes, setParentTypes] = useState([]);
    const [subTypes, setSubTypes] = useState([]);
    const [selectedParentType, setSelectedParentType] = useState("ALL");
    const [selectedSubType, setSelectedSubType] = useState("ALL");
    const [weeklySales, setWeeklySales] = useState([]);
    const [totalSalesThisMonth, setTotalSalesThisMonth] = useState(0);
    const [productSales, setProductSales] = useState([]);
    const [productSalesPage, setProductSalesPage] = useState({ content: [], totalPages: 1 }); // 상품별 매출 페이징 데이터
    const [productTypeSales, setProductTypeSales] = useState([]); // 상품 타입별 매출 데이터를 위한 상태 추가
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        fetchParentTypes();
    }, []);

    useEffect(() => {
        fetchSalesData();
    }, [currentPage, selectedParentType, selectedSubType]);

    const fetchParentTypes = () => {
        axios.get('http://localhost:8080/api/product-types/product-parent-types')
            .then(response => {
                setParentTypes(response.data);
            })
            .catch(error => console.error("Error fetching parent types:", error));
    };

    const fetchSubTypes = (parentType) => {
        axios.get(`http://localhost:8080/api/product-types/product-sub-types?parentType=${parentType}`)
            .then(response => {
                setSubTypes(response.data);
            })
            .catch(error => console.error("Error fetching sub types:", error));
    };

    const fetchSalesData = () => {
        fetchWeeklySalesThisMonth();
        fetchTotalSalesThisMonth();
        fetchProductSales(currentPage, selectedParentType, selectedSubType);
        fetchProductTypeSales(); // 상품 타입별 매출 데이터를 가져오는 함수 호출
    };

    const fetchWeeklySalesThisMonth = () => {
        axios.get('http://localhost:8080/api/sales/weekly-this-month')
            .then(response => {
                setWeeklySales(response.data);
            })
            .catch(error => console.error("Error fetching weekly sales:", error));
    };

    const fetchTotalSalesThisMonth = () => {
        axios.get('http://localhost:8080/api/sales/total-this-month')
            .then(response => {
                setTotalSalesThisMonth(response.data);
            })
            .catch(error => console.error("Error fetching total sales this month:", error));
    };

    const fetchProductSales = (page, parentType, subType) => {
        let url = `http://localhost:8080/api/sales/product?page=${page}`;
        if (parentType && parentType !== "ALL") {
            url += `&parentType=${parentType}`;
        }
        if (subType && subType !== "ALL") {
            url += `&subType=${subType}`;
        }
        axios.get(url)
            .then(response => {
                setProductSalesPage(response.data);
            })
            .catch(error => console.error("Error fetching product sales:", error));
    };

    const fetchProductTypeSales = () => {
        let url = 'http://localhost:8080/api/sales/product-type-sales';
        if (selectedParentType && selectedParentType !== "ALL") {
            url += `?parentType=${selectedParentType}`;
            if (selectedSubType && selectedSubType !== "ALL") {
                url += `&subType=${selectedSubType}`;
            }
        }
        axios.get(url)
            .then(response => {
                setProductTypeSales(response.data);
            })
            .catch(error => console.error("Error fetching product type sales:", error));
    };

    const handlePageClick = (data) => {
        setCurrentPage(data.selected);
    };

    const handleParentTypeChange = (e) => {
        const selectedParent = e.target.value;
        setSelectedParentType(selectedParent);
        setSelectedSubType("ALL");
        if (selectedParent === "ALL") {
            setSubTypes([]);
        } else {
            fetchSubTypes(selectedParent);
        }
    };

    const handleSubTypeChange = (e) => {
        setSelectedSubType(e.target.value);
    };

    const renderWeeklySales = () => {
        const maxWeek = Math.max(...weeklySales.map(ws => ws.week), 0);
        const weeks = Array.from({ length: maxWeek }, (_, i) => i + 1);
        return weeks.map(week => {
            const weekData = weeklySales.find(ws => ws.week === week);
            return (
                <tr key={week}>
                    <td>{week}주차</td>
                    <td>{weekData ? weekData.totalSalesAmount : 0}</td>
                </tr>
            );
        });
    };

    const renderProductSales = () => {
        return productSalesPage.content.map((product, index) => (
            <tr key={index}>
                <td>{product.productName}</td>
                <td>{product.totalSalesAmount}</td>
            </tr>
        ));
    };

    const renderProductTypeSales = () => {
        return productTypeSales.map((type, index) => (
            <tr key={index}>
                <td>{type.productType}</td>
                <td>{type.totalSalesAmount}</td>
            </tr>
        ));
    };

    return (
        <div>
            <h2>This Month's Sales</h2>
            <h3>Total Sales: {totalSalesThisMonth}</h3>
            {weeklySales.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Week</th>
                            <th>Total Sales Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderWeeklySales()}
                    </tbody>
                </table>
            ) : (
                <p>No sales data available for this month.</p>
            )}
            <h2>Product Sales</h2>
            <div>
                <label>Main Type: </label>
                <select value={selectedParentType} onChange={handleParentTypeChange}>
                    <option value="ALL">All</option>
                    {parentTypes.map((type, index) => (
                        <option key={index} value={type}>{type}</option>
                    ))}
                </select>
            </div>
            <div>
                <label>Sub Type: </label>
                <select value={selectedSubType} onChange={handleSubTypeChange} disabled={selectedParentType === "ALL"}>
                    <option value="ALL">All</option>
                    {subTypes.map((type, index) => (
                        <option key={index} value={type}>{type}</option>
                    ))}
                </select>
            </div>
            {productSalesPage.content.length > 0 ? (
                <>
                    <table>
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Total Sales Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            {renderProductSales()}
                        </tbody>
                    </table>
                    <ReactPaginate
                        previousLabel={"previous"}
                        nextLabel={"next"}
                        breakLabel={"..."}
                        breakClassName={"break-me"}
                        pageCount={productSalesPage.totalPages}
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={5}
                        onPageChange={handlePageClick}
                        containerClassName={"pagination"}
                        subContainerClassName={"pages pagination"}
                        activeClassName={"active"}
                    />
                </>
            ) : (
                <p>No product sales data available.</p>
            )}
            <h2>Product Type Sales</h2>
            {productTypeSales.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Product Type</th>
                            <th>Total Sales Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderProductTypeSales()}
                    </tbody>
                </table>
            ) : (
                <p>No product type sales data available.</p>
            )}
        </div>
    );
};

export default AdminSales;
