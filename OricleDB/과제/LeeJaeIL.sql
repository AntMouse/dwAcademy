/*
COURSE : �ڽ�
ENROLLMENT : ���
GRADE : ���
GRADE CONVERSION : ��� ����
GRADE TYPE : ��� ����
GRADE TYPE WEIGHT : ��� ���� �߷�
INSTRUCTOR : ����
SECTION : ����
STUDENT : �л�
ZIPCODE : �����ȣ
*/

-- �����Ϳ� �ð��� ǥ���ϱ�
-- �����Ϳ� �ð��� ����� �� �ż� �߰��߽��ϴ�.
-- �����Ϳ� �ð��� ����� �ȴٸ� �������� �ʾƵ� �˴ϴ�.
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-RRRR HH24:MI:SS';

-- 1��
SELECT * 
FROM SECTION
WHERE TO_CHAR(START_DATE_TIME, 'HH24:MI') = '10:30';

-- 2��
SELECT TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') AS DAY, SECTION_ID
FROM SECTION
WHERE SECTION_ID IN (99, 89, 105)
ORDER BY 
    CASE 
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'MON' THEN 1
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'TUE' THEN 2
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'WED' THEN 3
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'THU' THEN 4
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'FRI' THEN 5
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'SAT' THEN 6
        WHEN TO_CHAR(START_DATE_TIME, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'SUN' THEN 7
    END;

-- 3��
SELECT 
    DISTINCT TO_CHAR(NVL(COST, 0), '$999,999,990.99') AS COST
FROM 
    COURSE
ORDER BY 
    COST;
    
-- 4��
SELECT *
FROM GRADE_TYPE
WHERE EXTRACT(YEAR FROM CREATED_DATE) = 1998;

-- 5��
/* 
SELECR�� �ùٸ� Ű���尡 �ƴմϴ�. �ùٸ� Ű����� SELECT�Դϴ�.
�ڵ� �������� ';' ���ڰ� ���� �Ǿ����ϴ�. �������� ';' ���ڸ� �־���� �մϴ�.
���� �ڵ带 �ùٸ��� �����ϸ� ������ �����ϴ�.
*/
SELECT ZIP + 100
FROM ZIPCODE;

-- 6��
SELECT s.STUDENT_ID, e.ENROLL_DATE
FROM STUDENT s
JOIN ENROLLMENT e ON s.STUDENT_ID = e.STUDENT_ID
WHERE TO_CHAR(e.ENROLL_DATE, 'YYYY-MM-DD') = '2007-01-30';

-- 7��
/*
1. WHERE ��: �����ͺ��̽����� ���� ���� ����Ǵ� ���Դϴ�. ���ǿ� �´� �ุ�� �����մϴ�. 
���⼭�� TRUNC(ENROLL_DATE) > TO_DATE('2/16/2007', 'MM/DD/YYYY') ���ǿ� �´� �ุ�� �����մϴ�. 
��¥ �����͵� ũ�⸦ ���� �� �ְ�, ������ ��¥�� ������ ��¥ �߿��� ������ ��¥�� �� ū ���Դϴ�.
���� �� ������ 'ENROLL_DATE'�� '2007-02-16' ������ ���� �����մϴ�.

2. GROUP BY ��: WHERE �� ������ ����˴ϴ�. GROUP BY ���� ������ ���� �������� ���� �׷�ȭ�մϴ�. 
���⼭�� 'SECTION_ID'�� 'FINAL_GRADE' ���� �������� �׷�ȭ�˴ϴ�.

3. HAVING ��: GROUP BY �� ���Ŀ� ����˴ϴ�. �׷쿡 ���� ������ �����մϴ�. 
���⼭�� �׷쿡 ���� ���� ���� 5���� ū ��쿡�� �׷��� �����մϴ�.

���� SQL ���� ���� ������ WHERE -> GROUP BY -> HAVING �Դϴ�.
*/
SELECT SECTION_ID, COUNT(*), FINAL_GRADE FROM ENROLLMENT
WHERE TRUNC(ENROLL_DATE) > TO_DATE('2/16/2007', 'MM/DD/YYYY')
GROUP BY SECTION_ID, FINAL_GRADE
HAVING COUNT(*) > 5;

-- 8��
-- �ڽ� ����� ���� �ٸ� ����� ��ʸ� ��Ÿ�½��ϴ�.
SELECT COUNT(DISTINCT COST) AS UNIQUE_COST_COUNT
FROM COURSE;

-- 9��
SELECT COUNT(*) AS STUDENT_COUNT
FROM STUDENT s
JOIN ZIPCODE z ON s.ZIP = z.ZIP
WHERE z.ZIP = '10025';

-- 10��
SELECT EMPLOYER AS EMPLOYER_NAME, COUNT(*) AS STUDENT_COUNT
FROM STUDENT
GROUP BY EMPLOYER
HAVING COUNT(*) >= 4;

-- 11��
SELECT
    I.INSTRUCTOR_ID,
    I.FIRST_NAME,
    I.LAST_NAME,
    COUNT(S.SECTION_ID) AS COURSE_COUNT
FROM
    INSTRUCTOR I
LEFT JOIN
    SECTION S
ON
    I.INSTRUCTOR_ID = S.INSTRUCTOR_ID
GROUP BY
    I.INSTRUCTOR_ID, I.FIRST_NAME, I.LAST_NAME;

-- 12��
/*
�� SQL ������ SECTION ���̺��� �ߺ��� start_date_time�� location�� ������ ������ ã�� ���� �ذ��մϴ�.
��, ������ �ð��� ��ҿ��� �ߺ��� ������ �ִ��� Ȯ���ϰ��� �մϴ�. 
��������� COUNT(*)�� 1���� ū ���ǵ��� ��ȯ�մϴ�. 
�̸� ���� �ߺ��� ���ǵ��� �ĺ��� �� �ֽ��ϴ�.
*/
SELECT COUNT(*), START_DATE_TIME, location 
FROM section
GROUP BY START_DATE_TIME, location
HAVING COUNT(*) > 1;

-- 13��
-- 2���� �亯�� �ֽ��ϴ�.

-- 13-1 �߰� �б⸦ ���Ƿ� ����� ���
SELECT 
    S.COURSE_NO,
    C.DESCRIPTION AS COURSE_DESCRIPTION,
    MAX(G.NUMERIC_GRADE) AS HIGHEST_GRADE,
    CASE 
        WHEN COUNT(GTW.GRADE_TYPE_CODE) = 0 THEN NULL -- �߰� �бⰡ ���� ���
        ELSE
            TRUNC(S.START_DATE_TIME + ((MAX(GTW.NUMBER_PER_SECTION) / 2) * 7)) -- �߰� �б��� ������ ���
    END AS MIDTERM_START_DATE
FROM 
    SECTION S
JOIN 
    COURSE C ON S.COURSE_NO = C.COURSE_NO
LEFT JOIN 
    GRADE G ON S.SECTION_ID = G.SECTION_ID
LEFT JOIN 
    GRADE_TYPE_WEIGHT GTW ON S.SECTION_ID = GTW.SECTION_ID
GROUP BY 
    S.COURSE_NO, C.DESCRIPTION, S.START_DATE_TIME
ORDER BY 
    S.COURSE_NO;
    
-- 13-2 �߰��б⸦ ���Ƿ� ������� ���� ���
SELECT
    c.COURSE_NO,
    c.DESCRIPTION AS COURSE_DESCRIPTION,
    MAX(g.NUMERIC_GRADE) AS HIGHEST_GRADE
FROM
    SECTION s
    JOIN COURSE c ON s.COURSE_NO = c.COURSE_NO
    LEFT JOIN GRADE g ON s.SECTION_ID = g.SECTION_ID
GROUP BY
    c.COURSE_NO, c.DESCRIPTION
ORDER BY c.COURSE_NO;

-- 14��
/*
SELECT SUM(order_amount) AS "Order Total"
FROM customer_order;

�� ������ "CUSTOMER_ORDER" ���̺��� � Ư�� ���� ��ȯ�ϴ� ���� �ƴ϶�, 
"CUSTOMER_ORDER" ���̺��� "order_amount" ���� �հ踦 SUM() �Լ��� ����� 
��ȯ�ϹǷ� ����� ��ȯ�Ǵ� ���� ���� 1�Դϴ�.
*/