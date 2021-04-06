-- 전사원을 대상으로 그룹핑
select	count(*),avg(sal),max(sal),min(sal)
from	emp;

select 	count(*), count(comm), sum(comm), avg(comm), sum(comm)/count(*), sum(comm)/count(comm)
from	emp;

-- 매니저가 지정된 사람들의 수
select 	count(mgr)
from	emp;

-- 매니저 수
select 	count(distinct mgr)
from	emp;

select 		deptno,count(*),sum(sal)
from		emp
group by	deptno;

-- group by 절에 컬럼순서나 별칭 사용가능(mysql만)
select 		deptno dno,count(*),sum(sal)
from		emp
group by	dno;
-- group by	1;

-- 부서별 최대급여와 최대급여를 받는 사원 : X
select 		deptno,max(sal),ename
from 		emp
group by 	deptno;

-- 부서별 이름별 최대급여와 해당사원의 이름
-- 논리적 오류
select 		deptno,max(sal),ename
from 		emp
group by 	deptno,ename;


select deptno,max(sal)
from	emp
group by deptno;

select 	deptno, sal,ename
from	emp
where	(ifnull(deptno,0),sal)	in(
    select 	ifnull(deptno,0) ,max(sal)
    from	emp
    group by deptno
);

-- 동일입사월별 사원의 사원수, 급여평균
select 	month(HIREDATE), count(*), round(avg(sal),2)
from		emp
group by	month(HIREDATE);

-- 부서별 업무별 사원 수
select 	deptno,job,count(*)
from		emp
group by 	deptno,job;

-- 부서별 사원수가 5명 이상인 부서 조회
select		deptno,count(*)
from		emp
            -- where 		count(*) >=5  : 그룹함수는 where절에 사용불가(그룹핑 이후에 사용가능)
group by 	deptno
having 	count(*) >=5 ;


-- 1000보다 월급여를 많이 받는 사원들을 대상으로 부서별 사원수가 5명 이상인 부서 조회
select		deptno,count(*)
from		emp
where 		sal > 1000
group by 	deptno
having 	count(*) >=5 ;

-- 같은 입사월을 갖는 사원들의 평균급여, (단, 같은 입사월의 사원수가 2명 이상이며  평균급여가 2000이상인 경우)
select		month(hiredate),count(*), avg(sal)
from		emp
group by	month(hiredate)
having		count(*)>=2 and avg(sal)>=2000;

-- 부서별 평균급여가 최대인 부서
-- mysql은 그룹함수의 중첩사용을 지원하지 않음
-- error
select		deptno, avg(sal)
from		emp
group by 	deptno
-- having		avg(sal) = 부서별평균급여중 최대평균값
having		avg(sal) = (
    select		max(avg(sal))
    from		emp
    group by 	deptno
);

-- 방법1
select		deptno, avg(sal)
from		emp
group by 	deptno
-- having		avg(sal) = 부서별평균급여중 최대평균값
having		avg(sal) = (
    select max(e.asal)
    from   (
               select		avg(sal) asal
               from		emp
               group by 	deptno
           ) e
);
-- 방법2
select		deptno, avg(sal)
from		emp
group by 	deptno
-- having		avg(sal) = 부서별평균급여중 최대평균값
having		avg(sal) >= all (
    select		avg(sal) asal
    from		emp
    group by 	deptno
);

-- 방법3
select		deptno, avg(sal)
from		emp
group by 	deptno
order by	avg(sal) desc
    limit 		1;


-- case 표현식 when  then 값1 else 값2  end
-- else가 생략되면 null이 반환
-- 조건이 동등비교
-- 	case 표현식 when 값1 then 결과1 when 값2 then 결과2 else 결과3 end
-- 조건이 동등비교가 아닐때
-- 	case  when 조건식1 then 결과1 when 조건식2 then 결과2 else 결과3 end

select '070314-3111111' ,
       case substr('070314-3111111',8,1)
           when '1' then '남'
           when '3' then '남'
           else '여'
           end 성별
from	dual;

-- sal
-- 4000 ~~ : A
-- 2000 ~~ 3999 : B
-- 0 ~~ 1999 : C

select	sal,
          case when sal >= 4000 then 'A'
               when sal >= 2000 then 'B'
               else 'C'
              end  급여등급
from	emp;

-- if
-- else if
-- else

-- if
-- else
-- 	if
--     else

select sal, if( sal >= 4000 , 'A' ,  if(sal >= 2000,'B','C')  ) 급여등급
from emp;

-- 부서별 업무별 급여합
select deptno, job, sum(sal)
from	emp
group by deptno, job;


-- deptno   clerk   salesman   manager   analyst  president
-- 10  		 1000				2000
-- 20  	 						2000		1000

-- step1
-- ename deptno  job	    sal
-- king  10   	president	5000
-- smith 20		clerk		800

-- ename deptno 	clerk   salesman   manager   analyst  president
-- 	king  	10   											5000
-- 	miller  10   	1300
-- 	smith  	20   	800

select	ename,deptno,job,sal,
          case job when 'CLERK' then sal 	end CLERK,
          case job when 'MANAGER' then sal end MANAGER,
          case job when 'PRESIDENT' then sal 	end PRESIDENT,
          case job when 'ANALYST' then sal 	end ANALYST,
          case job when 'SALESMAN' then sal 	end SALESMAN
from	emp;


-- step 2.
-- 부서기준으로 grouping
-- ename deptno 	clerk   salesman   manager   analyst  president
-- 	king  	10   											5000
-- 	miller  10   	1300
-- 	smith  	20   	800

select	deptno,
          sum(case job when 'CLERK' then sal 	end) CLERK,
          sum(case job when 'MANAGER' then sal end) MANAGER,
          sum(case job when 'PRESIDENT' then sal 	end) PRESIDENT,
          sum(case job when 'ANALYST' then sal 	end) ANALYST,
          sum(case job when 'SALESMAN' then sal 	end) SALESMAN
from	emp
where	job is not null
group by deptno;

-- 부서별 업무별 사원수
select	deptno,
          count(case job when 'CLERK' then sal 	end) CLERK,
          count(case job when 'MANAGER' then sal end) MANAGER,
          count(case job when 'PRESIDENT' then sal 	end) PRESIDENT,
          count(case job when 'ANALYST' then sal 	end) ANALYST,
          count(case job when 'SALESMAN' then sal 	end) SALESMAN
from	emp
where	job is not null
group by deptno;

create view v_job_emp
as select	ename,deptno,job,sal,
             case job when 'CLERK' then sal 	end CLERK,
             case job when 'MANAGER' then sal end MANAGER,
             case job when 'PRESIDENT' then sal 	end PRESIDENT,
             case job when 'ANALYST' then sal 	end ANALYST,
             case job when 'SALESMAN' then sal 	end SALESMAN
   from	emp;

-- view 활용
select deptno, sum(clerk), sum(manager), sum(president), sum(analyst), sum(salesman)
from  v_job_emp
where job is not null
group by deptno;

select deptno, count(clerk), count(manager), count(president), count(analyst), count(salesman)
from v_job_emp
where job is not null
group by deptno;

-- inline view 활용
select s.deptno, sum(clerk), sum(manager), sum(president), sum(analyst), sum(salesman)
from (
         select	ename,deptno,job,sal,
                   case job when 'CLERK' then sal 	end CLERK,
                   case job when 'MANAGER' then sal end MANAGER,
                   case job when 'PRESIDENT' then sal 	end PRESIDENT,
                   case job when 'ANALYST' then sal 	end ANALYST,
                   case job when 'SALESMAN' then sal 	end SALESMAN
         from	emp
     ) s
where s.job is not null
group by s.deptno;

select s.deptno, count(clerk), count(manager), count(president), count(analyst), count(salesman)
from (
         select	ename,deptno,job,sal,
                   case job when 'CLERK' then sal 	end CLERK,
                   case job when 'MANAGER' then sal end MANAGER,
                   case job when 'PRESIDENT' then sal 	end PRESIDENT,
                   case job when 'ANALYST' then sal 	end ANALYST,
                   case job when 'SALESMAN' then sal 	end SALESMAN
         from	emp
     ) s
where s.job is not null
group by s.deptno;


