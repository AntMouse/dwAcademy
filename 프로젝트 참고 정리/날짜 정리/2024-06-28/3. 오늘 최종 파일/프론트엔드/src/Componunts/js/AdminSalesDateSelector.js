// src/componunts/js/AdminSalesDateSelector.js
import React, { useEffect, useState } from 'react';

// 현재 연도 계산
const currentYear = new Date().getFullYear();

const AdminSalesDateSelector = ({
    selectedPeriod,
    startDate,
    endDate,
    setStartDate,
    setEndDate,
    handleCustomDateChange
}) => {
    const [years, setYears] = useState([]);
    const [months, setMonths] = useState([]);
    const [quarters, setQuarters] = useState([1, 2, 3, 4]);

    useEffect(() => {
        // -20년부터 현재 연도까지의 배열 생성
        const yearArray = Array.from({ length: 21 }, (_, i) => currentYear - 20 + i);
        setYears(yearArray);

        // 1월부터 12월까지의 배열 생성
        const monthArray = Array.from({ length: 12 }, (_, i) => i + 1);
        setMonths(monthArray);
    }, []);

    // 드롭다운 메뉴 값이 바뀔 때 기존의 값 유지
    useEffect(() => {
        if (selectedPeriod !== 'custom' && startDate === '') {
            setStartDate(currentYear);
        }
    }, [selectedPeriod, setStartDate]);

    const isCustom = selectedPeriod === 'custom';
    const isWeeklyOrMonthly = selectedPeriod === 'weekly' || selectedPeriod === 'monthly';
    const isQuarterly = selectedPeriod === 'quarterly';
    const isYearly = selectedPeriod === 'yearly';
    const isToday = selectedPeriod === 'today';

    return (
        <div>
            {(isCustom || isWeeklyOrMonthly || isQuarterly || isYearly || isToday) && (
                <>
                    {isCustom ? (
                        <>
                            <input
                                type="datetime-local"
                                value={startDate}
                                onChange={(e) => setStartDate(e.target.value)}
                                disabled={!isCustom}
                            />
                            <input
                                type="datetime-local"
                                value={endDate}
                                onChange={(e) => setEndDate(e.target.value)}
                                disabled={!isCustom}
                            />
                        </>
                    ) : (
                        <>
                            <select
                                value={startDate}
                                onChange={(e) => setStartDate(e.target.value)}
                                disabled={isToday}
                            >
                                <option value="">연도 선택</option>
                                {years.map((year) => (
                                    <option key={year} value={year}>
                                        {year}
                                    </option>
                                ))}
                            </select>
                            {isWeeklyOrMonthly ? (
                                <select
                                    value={endDate}
                                    onChange={(e) => setEndDate(e.target.value)}
                                >
                                    <option value="">월 선택</option>
                                    {months.map((month) => (
                                        <option key={month} value={month}>
                                            {month}월
                                        </option>
                                    ))}
                                </select>
                            ) : isQuarterly ? (
                                <select
                                    value={endDate}
                                    onChange={(e) => setEndDate(e.target.value)}
                                >
                                    <option value="">분기 선택</option>
                                    {quarters.map((quarter) => (
                                        <option key={quarter} value={quarter}>
                                            {quarter}분기
                                        </option>
                                    ))}
                                </select>
                            ) : (
                                <select disabled>
                                    <option>월 선택</option>
                                </select>
                            )}
                        </>
                    )}
                    <button onClick={handleCustomDateChange}>조회</button>
                </>
            )}
        </div>
    );
};

export default AdminSalesDateSelector;
