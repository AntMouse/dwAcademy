SELECT *
FROM EMP
WHERE DEPTNO IN (20, 30);

SELECT *
FROM EMP
WHERE SAL IN (SELECT MAX(SAL)
                FROM EMP
                GROUP BY DEPTNO);
                
SELECT MAX(SAL)
FROM EMP
GROUP BY DEPTNO;

SELECT *
FROM EMP
WHERE SAL = ANY (SELECT MAX(SAL)
                    FROM EMP
                    GROUP BY DEPTNO);
                    
SELECT *
FROM EMP
WHERE SAL = SOME (SELECT MAX(SAL)
                    FROM EMP
                    GROUP BY DEPTNO);
                    
SELECT *
FROM EMP
WHERE SAL < ANY (SELECT SAL
                FROM EMP
                WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;

SELECT SAL
FROM EMP
WHERE DEPTNO = 30;

SELECT *
FROM EMP
WHERE SAL < (SELECT MAX(SAL)
                FROM EMP
                WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;

SELECT *
FROM EMP
WHERE SAL > ANY (SELECT SAL
                    FROM EMP
                    WHERE DEPTNO = 30);
                    
SELECT *
FROM EMP
WHERE SAL < ALL (SELECT SAL
                    FROM EMP
                    WHERE DEPTNO = 30);
                    
SELECT *
FROM EMP
WHERE SAL > ALL (SELECT SAL
                    FROM EMP
                    WHERE DEPTNO = 30);
                    
SELECT *
FROM EMP
WHERE EXISTS (SELECT DNAME
                FROM DEPT
                WHERE DEPTNO = 10);
                
SELECT *
FROM EMP;
                
SELECT *
FROM EMP
WHERE EXISTS (SELECT DNAME
                FROM DEPT
                WHERE DEPTNO = 50);
                
-- ¹®Á¦
SELECT *
FROM EMP
WHERE HIREDATE < ALL (SELECT HIREDATE
                    FROM EMP
                    WHERE DEPTNO = 10);