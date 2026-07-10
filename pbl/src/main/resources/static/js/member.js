// ─── Member API ────────────────────────────────────────────────
const MemberAPI = {
    getAll: (part) => httpFetch('GET', part ? `/members?part=${encodeURIComponent(part)}` : '/members'),
    getById: (id) => httpFetch('GET', `/members/${id}`),
    createLion: (data) => httpFetch('POST', '/members/lions', data),
    createStaff: (data) => httpFetch('POST', '/members/staffs', data),
    updateLion: (id, data) => httpFetch('PUT', `/members/lions/${id}`, data),
    updateStaff: (id, data) => httpFetch('PUT', `/members/staffs/${id}`, data),
    delete: (id) => httpFetch('DELETE', `/members/${id}`),
};

// ─── 멤버 등록 ─────────────────────────────────────────────────
async function createMember() {
    const role = document.getElementById('member-role-select').value;
    const name = document.getElementById('member-name').value.trim();
    const major = document.getElementById('member-major').value.trim();
    const generation = parseInt(document.getElementById('member-generation').value);
    const part = document.getElementById('member-part').value;

    if (!name || !major || !generation) { showToast('이름, 전공, 기수를 입력해주세요.', 'error'); return; }

    let result;
    if (role === 'lion') {
        const studentId = document.getElementById('member-studentId').value.trim();
        if (!studentId) { showToast('학번을 입력해주세요.', 'error'); return; }
        result = await MemberAPI.createLion({ name, major, generation, part, studentId });
    } else {
        const position = document.getElementById('member-position').value;
        result = await MemberAPI.createStaff({ name, major, generation, part, position });
    }

    if (result) {
        showToast(`${result.name} 등록 완료!`, 'success');
        clearMemberForm();
        loadMembers();
    }
}

function clearMemberForm() {
    ['member-name', 'member-major', 'member-generation', 'member-studentId'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = '';
    });
}

// ─── 멤버 조회 및 목록 렌더링 ─────────────────────────────────
async function loadMembers() {
    const part = document.getElementById('filter-part').value;
    const members = await MemberAPI.getAll(part || null);
    if (!members) return;
    renderMemberTable(members);
}

function renderMemberTable(members) {
    const container = document.getElementById('member-list');
    if (!members.length) { container.innerHTML = '<p class="empty">등록된 멤버가 없습니다.</p>'; return; }

    container.innerHTML = `
        <div class="table-wrap">
        <table>
            <thead><tr>
                <th>ID</th><th>이름</th><th>역할</th><th>전공</th><th>기수</th><th>파트</th><th>학번/직책</th><th>관리</th>
            </tr></thead>
            <tbody>
            ${members.map(m => `
                <tr>
                    <td>${m.id}</td>
                    <td><strong>${m.name}</strong></td>
                    <td><span class="badge ${m.roleName === '아기사자' ? 'badge-lion' : 'badge-staff'}">${m.roleName}</span></td>
                    <td>${m.major || ''}</td>
                    <td>${m.generation}기</td>
                    <td>${m.part || ''}</td>
                    <td>${m.studentId || m.position || '-'}</td>
                    <td>
                        <button class="btn btn-secondary btn-sm" onclick="startEditMember(${m.id}, '${m.roleName}', ${JSON.stringify(m).replace(/"/g, '&quot;')})">수정</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteMember(${m.id})">삭제</button>
                    </td>
                </tr>
            `).join('')}
            </tbody>
        </table>
        </div>
    `;
}

// ─── 멤버 수정 ─────────────────────────────────────────────────
function startEditMember(id, roleName, m) {
    const major = prompt('전공:', m.major || '');
    if (major === null) return;
    const generation = prompt('기수:', m.generation || '');
    if (generation === null) return;
    const part = prompt('파트 (백엔드/프론트엔드/기획디자인):', m.part || '');
    if (part === null) return;

    if (roleName === '아기사자') {
        const studentId = prompt('학번:', m.studentId || '');
        if (studentId === null) return;
        doUpdateLion(id, { major, generation: parseInt(generation), part, studentId });
    } else {
        const position = prompt('직책 (대표/부대표/일반):', m.position || '');
        if (position === null) return;
        doUpdateStaff(id, { major, generation: parseInt(generation), part, position });
    }
}

async function doUpdateLion(id, data) {
    const result = await MemberAPI.updateLion(id, data);
    if (result) { showToast('수정 완료!', 'success'); loadMembers(); }
}

async function doUpdateStaff(id, data) {
    const result = await MemberAPI.updateStaff(id, data);
    if (result) { showToast('수정 완료!', 'success'); loadMembers(); }
}

// ─── 멤버 삭제 ─────────────────────────────────────────────────
async function deleteMember(id) {
    if (!confirm(`ID ${id} 멤버를 삭제하시겠습니까?`)) return;
    const result = await httpFetch('DELETE', `/members/${id}`);
    if (result !== null || true) {
        // 204 No Content 이므로 result가 null이어도 성공
        showToast('삭제 완료!', 'success');
        loadMembers();
    }
}

// 페이지 로드 시 멤버 목록 조회
window.addEventListener('DOMContentLoaded', loadMembers);
