// src/componunts/js/AdminSalesDateSelector.js
import React from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const AdminSalesDateSelector = ({ selectedPeriod, startDate, endDate, setStartDate, setEndDate, handleCustomDateChange }) => {
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
                            <DatePicker
                                selected={startDate}
                                onChange={(date) => setStartDate(date)}
                                showTimeSelect
                                dateFormat="Pp"
                                disabled={!isCustom}
                            />
                            <DatePicker
                                selected={endDate}
                                onChange={(date) => setEndDate(date)}
                                showTimeSelect
                                dateFormat="Pp"
                                disabled={!isCustom}
                            />
                        </>
                    ) : (
                        <>
                            <DatePicker
                                selected={startDate}
                                onChange={(date) => setStartDate(date)}
                                showYearPicker
                                dateFormat="yyyy"
                                placeholderText="연도 선택"
                                disabled={isToday}
                            />
                            {isWeeklyOrMonthly && (
                                <DatePicker
                                    selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    showMonthYearPicker
                                    dateFormat="MM/yyyy"
                                    placeholderText="월 선택"
                                    disabled={isToday || isYearly}
                                />
                            )}
                            {isQuarterly && (
                                <select value={endDate} onChange={(e) => setEndDate(e.target.value)} disabled={isToday}>
                                    <option value="1">1분기</option>
                                    <option value="2">2분기</option>
                                    <option value="3">3분기</option>
                                    <option value="4">4분기</option>
                                </select>
                            )}
                            {isYearly && (
                                <DatePicker
                                    selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    showMonthYearPicker
                                    dateFormat="MM/yyyy"
                                    placeholderText="월 선택"
                                    disabled
                                />
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
